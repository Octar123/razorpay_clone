package com.codingShuttle.razorpay.common.dto;

import java.util.UUID;

public record WebhookTarget(UUID configId, String targetUrl, String WebhookSecret) {
}
