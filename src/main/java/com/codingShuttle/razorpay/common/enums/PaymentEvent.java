package com.codingShuttle.razorpay.common.enums;

public enum PaymentEvent {
    AUTHORIZE_ATTEMPT,
    AUTHORIZE_SUCCESS,
    AUTHORIZE_FAIL,
    CAPTURE_ATTEMPT,
    CAPTURE_SUCCESS,
    CAPTURE_FAIL,
    REFUND_INIT,
    REFUND_COMPLETE,
    SETTLE,
    CANCEL,
    CAPTURE_TIMEOUT
}
