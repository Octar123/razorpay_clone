package com.codingShuttle.razorpay.payment.entity;

import com.codingShuttle.razorpay.common.entity.BaseEntity;
import com.codingShuttle.razorpay.common.enums.PaymentActor;
import com.codingShuttle.razorpay.common.enums.PaymentEvent;
import com.codingShuttle.razorpay.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_transition_log", indexes = {
        @Index(name = "idx_payment_trasaction_log_payment_id", columnList = "payment_id")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentTransitionLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private PaymentStatus fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentEvent event;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private PaymentStatus toStatus;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private PaymentActor actor;

    @Column(nullable = false)
    private LocalDateTime occurredAt;
}
