package com.codingShuttle.razorpay.payment.processor;

import com.codingShuttle.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.codingShuttle.razorpay.payment.processor.dto.PaymentProcessorResponse;

public interface PaymentProcessor {

    PaymentProcessorResponse charge(PaymentProcessorRequest request);
}
