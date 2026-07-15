package com.codingShuttle.razorpay.merchant.service;

import com.codingShuttle.razorpay.merchant.dto.request.LoginRequest;
import com.codingShuttle.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.codingShuttle.razorpay.merchant.dto.response.LoginResponse;
import com.codingShuttle.razorpay.merchant.dto.response.MerchantResponse;
import jakarta.validation.Valid;

public interface AuthService {
    MerchantResponse signup(MerchantSignupRequest request);

    LoginResponse login(LoginRequest request);
}
