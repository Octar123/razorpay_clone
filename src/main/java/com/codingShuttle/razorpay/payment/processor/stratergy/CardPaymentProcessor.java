package com.codingShuttle.razorpay.payment.processor.stratergy;

import com.codingShuttle.razorpay.payment.processor.PaymentProcessor;
import com.codingShuttle.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.codingShuttle.razorpay.payment.processor.dto.PaymentProcessorResponse;

public class CardPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        return null;
    }
}
