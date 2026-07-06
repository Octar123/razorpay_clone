package com.codingShuttle.razorpay.payment.gateway.adapter;

import com.codingShuttle.razorpay.payment.gateway.PaymentAdapter;
import com.codingShuttle.razorpay.payment.gateway.dto.PaymentRequest;
import com.codingShuttle.razorpay.payment.gateway.dto.PaymentResult;

import java.util.UUID;

public class CardPaymentAdapter implements PaymentAdapter {
    @Override
    public PaymentResult initiate(PaymentRequest request) {
        return null;
    }

    @Override
    public PaymentResult capture(UUID paymentId) {
        return null;
    }
}
