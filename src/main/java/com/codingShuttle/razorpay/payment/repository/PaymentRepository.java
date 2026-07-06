package com.codingShuttle.razorpay.payment.repository;

import com.codingShuttle.razorpay.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findByOrder_Id(UUID orderId);

    Optional<Payment> findByIdAndMerchantId(UUID paymentId, UUID merchantId);
}
