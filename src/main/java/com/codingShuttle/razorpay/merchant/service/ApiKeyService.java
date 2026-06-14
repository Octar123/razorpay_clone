package com.codingShuttle.razorpay.merchant.service;

import com.codingShuttle.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.codingShuttle.razorpay.merchant.dto.response.ApiKeyCreateResponse;

import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request);
}
