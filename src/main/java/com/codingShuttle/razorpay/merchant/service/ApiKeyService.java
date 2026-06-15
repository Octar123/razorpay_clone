package com.codingShuttle.razorpay.merchant.service;

import com.codingShuttle.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.codingShuttle.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.codingShuttle.razorpay.merchant.dto.response.ApiKeyResponse;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request);

    List<ApiKeyResponse> listByMerchant(UUID merchantId);

    void revoke(UUID merchantId, UUID keyId);

    ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId);
}
