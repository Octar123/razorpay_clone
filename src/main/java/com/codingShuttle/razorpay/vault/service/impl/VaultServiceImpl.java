package com.codingShuttle.razorpay.vault.service.impl;

import com.codingShuttle.razorpay.common.entity.Money;
import com.codingShuttle.razorpay.common.enums.CardBrand;
import com.codingShuttle.razorpay.common.exception.ResourceNotFoundException;
import com.codingShuttle.razorpay.common.util.RandomizerUtil;
import com.codingShuttle.razorpay.payment.processor.PaymentProcessor;
import com.codingShuttle.razorpay.payment.processor.PaymentProcessorRouter;
import com.codingShuttle.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.codingShuttle.razorpay.payment.processor.dto.PaymentProcessorResponse;
import com.codingShuttle.razorpay.vault.config.VaultEncryptionConfig;
import com.codingShuttle.razorpay.vault.dto.request.TokenizeRequest;
import com.codingShuttle.razorpay.vault.dto.response.TokenizeResponse;
import com.codingShuttle.razorpay.vault.entity.CardToken;
import com.codingShuttle.razorpay.vault.entity.VaultCard;
import com.codingShuttle.razorpay.vault.repository.CardTokenRepository;
import com.codingShuttle.razorpay.vault.repository.VaultCardRepository;
import com.codingShuttle.razorpay.vault.service.VaultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaultServiceImpl implements VaultService {

    private final CardTokenRepository cardTokenRepository;
    private final VaultCardRepository vaultCardRepository;
    private final BytesEncryptor dekEncrypter;
    private final PaymentProcessorRouter paymentProcessorRouter;

    @Override
    @Transactional
    public TokenizeResponse tokenize(TokenizeRequest request, UUID merchantId) {

        String lastFour = request.pan().substring(request.pan().length() - 4);
        String bin = request.pan().substring(0, 6);
        CardBrand cardBrand = detect(request.pan());

        byte[] dek = KeyGenerators.secureRandom(32).generateKey();
        byte[] encryptedPan = VaultEncryptionConfig.panEncrypter(dek)
                .encrypt(request.pan().getBytes(StandardCharsets.UTF_8));
        byte[] encryptedDek = dekEncrypter.encrypt(dek);

        VaultCard vaultCard = vaultCardRepository.save(VaultCard.builder()
                .brand(cardBrand)
                .expiryYear(request.expiryYear().toString())
                .expiryMonth(request.expiryMonth().toString())
                .bin(bin)
                .lastFour(lastFour)
                .encryptedDek(encryptedDek)
                .encryptedPan(encryptedPan)
                .cardHolderName(request.cardHolderName())
                .build());

        String token = "tok_"+ RandomizerUtil.randomBase64(32);

        cardTokenRepository.save(CardToken.builder()
                        .vaultCard(vaultCard)
                        .token(token)
                        .customer(request.customerId())
                        .merchant(merchantId)
                .build());

        return new TokenizeResponse(token, lastFour, cardBrand, request.expiryMonth(), request.expiryYear());
    }

    @Override
    public PaymentProcessorResponse charge(UUID paymentId, String token,
                                           Money amount, Map<String, Object> methodDetails) {
        CardToken cardToken = cardTokenRepository.findByTokenAndRevokedAtIsNull(token)
                .orElseThrow(() -> new ResourceNotFoundException("CardToken", token));

        VaultCard vaultCard = cardToken.getVaultCard();
        byte[] panBytes = null;

        try {
            byte[] dek = dekEncrypter.decrypt(vaultCard.getEncryptedDek());
            panBytes = VaultEncryptionConfig.panEncrypter(dek).decrypt(vaultCard.getEncryptedPan());

            String pan = new String(panBytes, StandardCharsets.UTF_8);
            String expiry = vaultCard.getExpiryMonth() + "/" + vaultCard.getExpiryYear();

            PaymentProcessorRequest paymentProcessorRequest = PaymentProcessorRequest
                    .card(paymentId, pan, expiry, amount, methodDetails);

            PaymentProcessorResponse response = paymentProcessorRouter.charge(paymentProcessorRequest);

            log.info("Vault charge registered, token={}****", token.substring(0, 4));


            return response;
        }catch (Exception e){
            log.warn("Vault charge failed, token={}****", token.substring(0, 4));
            return new PaymentProcessorResponse.Failure("VAULT_CHARGE_FAILED", e.getMessage());
        }finally {
            if(panBytes != null) Arrays.fill(panBytes, (byte) 0);
        }
    }

    private CardBrand detect(String pan) {
        if (pan.startsWith("4")) return CardBrand.VISA;
        if (pan.startsWith("5") || pan.startsWith("2")) return CardBrand.MASTERCARD;
        if (pan.startsWith("37") || pan.startsWith("34")) return CardBrand.AMEX;
        return CardBrand.RUPAY;
    }
}
