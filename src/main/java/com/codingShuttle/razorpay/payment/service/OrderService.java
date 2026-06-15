package com.codingShuttle.razorpay.payment.service;

import com.codingShuttle.razorpay.payment.dto.request.CreateOrderRequest;
import com.codingShuttle.razorpay.payment.dto.response.OrderResponse;

import java.util.UUID;

public interface OrderService {
    OrderResponse create(UUID merchantId, CreateOrderRequest request);
}
