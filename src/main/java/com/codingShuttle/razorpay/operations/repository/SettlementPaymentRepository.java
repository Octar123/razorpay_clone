package com.codingShuttle.razorpay.operations.repository;

import com.codingShuttle.razorpay.operations.entity.SettlementPayment;
import com.codingShuttle.razorpay.operations.entity.SettlementPaymentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementPaymentRepository extends JpaRepository<SettlementPayment, SettlementPaymentId> {
}
