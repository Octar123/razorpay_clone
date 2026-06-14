package com.codingShuttle.razorpay.merchant.dto.request;

import com.codingShuttle.razorpay.common.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment
) {
}
