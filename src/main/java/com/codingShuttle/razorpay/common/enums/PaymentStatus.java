package com.codingShuttle.razorpay.common.enums;

public enum PaymentStatus {
    CREATED,
    AUTHORIZING,
    AUTHORIZED,
    CAPTURING,
    CAPTURED,
    REFUNDED,
    PARTIALLY_REFUNDED,
    SETTLED,
    AUTH_EXPIRED,
    FAILED
}
