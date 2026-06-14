package com.codingShuttle.razorpay.merchant.dto.response;

import com.codingShuttle.razorpay.common.enums.Environment;

import java.util.UUID;

public record ApiKeyCreateResponse(
        UUID id,
        String keyId,
        String keySecret,
        Environment environment
) {
}
