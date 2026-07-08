package com.codingShuttle.razorpay.vault.service;

import com.codingShuttle.razorpay.common.entity.Money;
import com.codingShuttle.razorpay.payment.processor.dto.PaymentProcessorResponse;
import com.codingShuttle.razorpay.vault.dto.request.TokenizeRequest;
import com.codingShuttle.razorpay.vault.dto.response.TokenizeResponse;

import java.util.Map;
import java.util.UUID;

public interface VaultService {


    TokenizeResponse tokenize(TokenizeRequest request, UUID merchantId);

    PaymentProcessorResponse charge(UUID paymentId, String token, Money amount, Map<String, Object> methodDetails);
}
