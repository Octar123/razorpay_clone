package com.codingShuttle.razorpay.payment.processor.dto;

import com.codingShuttle.razorpay.common.entity.Money;
import com.codingShuttle.razorpay.common.enums.PaymentMethod;

import java.util.Map;

public record PaymentProcessorRequest(
        PaymentMethod method,
        Money amount,
        Map<String, Object> methodDetails
) {
}
