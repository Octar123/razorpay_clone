package com.codingShuttle.razorpay.payment.repository;

import com.codingShuttle.razorpay.common.enums.OutboxStatus;
import com.codingShuttle.razorpay.payment.entity.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, UUID> {

    List<OutboxEvent> findByStatusOrderByCreatedAtAsc(OutboxStatus status);
}
