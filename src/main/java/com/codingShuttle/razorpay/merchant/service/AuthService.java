package com.codingShuttle.razorpay.merchant.service;

import com.codingShuttle.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.codingShuttle.razorpay.merchant.dto.response.MerchantResponse;

public interface AuthService {
    MerchantResponse signup(MerchantSignupRequest request);
}
