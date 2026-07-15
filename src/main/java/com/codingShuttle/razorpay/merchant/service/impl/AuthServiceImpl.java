package com.codingShuttle.razorpay.merchant.service.impl;

import com.codingShuttle.razorpay.common.enums.MerchantStatus;
import com.codingShuttle.razorpay.common.enums.UserRole;
import com.codingShuttle.razorpay.common.exception.DuplicateResourceException;
import com.codingShuttle.razorpay.common.exception.ResourceNotFoundException;
import com.codingShuttle.razorpay.merchant.dto.request.LoginRequest;
import com.codingShuttle.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.codingShuttle.razorpay.merchant.dto.response.LoginResponse;
import com.codingShuttle.razorpay.merchant.dto.response.MerchantResponse;
import com.codingShuttle.razorpay.merchant.entity.AppUser;
import com.codingShuttle.razorpay.merchant.entity.Merchant;
import com.codingShuttle.razorpay.merchant.mapper.MerchantMapper;
import com.codingShuttle.razorpay.merchant.repository.AppUserRepository;
import com.codingShuttle.razorpay.merchant.repository.MerchantRepository;
import com.codingShuttle.razorpay.merchant.security.JwtUtil;
import com.codingShuttle.razorpay.merchant.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl  implements AuthService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;
    private final MerchantMapper merchantMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public MerchantResponse signup(MerchantSignupRequest request) {

        if(merchantRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("DUPLICATE_MERCHANT_EMAIL","Merchant with email already exists: " + request.email());
        }

//        Merchant merchant = Merchant.builder()
//                .businessName(request.businessName())
//                .businessType(request.businessType())
//                .name(request.name())
//                .email(request.email())
//                .status(MerchantStatus.PENDING_KYC)
//                .build();

        Merchant merchant = merchantMapper.toEntityFromSignUpRequest(request);
        merchant.setStatus(MerchantStatus.PENDING_KYC);

        merchant = merchantRepository.save(merchant);

        AppUser appUser = AppUser.builder()
                .email(request.email())
                .merchant(merchant)
                .passwordHash(passwordEncoder.encode(request.password()))
                .role(UserRole.OWNER)
                .build();

        appUser = appUserRepository.save(appUser);
        return merchantMapper.toResponse(merchant);
//        return new MerchantResponse(merchant.getId(), merchant.getName(),
//                merchant.getEmail(), merchant.getBusinessName(),
//                merchant.getBusinessType(), merchant.getStatus());

    }

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        AppUser appUser = appUserRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("User", request.email()));

        String token = jwtUtil.generateAccessToken(request.email(), appUser.getMerchant().getId(),
                appUser.getRole().toString());

        return new LoginResponse(token);
    }
}
