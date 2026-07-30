package com.codingShuttle.razorpay.payment.outbox;

import com.codingShuttle.razorpay.common.enums.OutboxStatus;
import com.codingShuttle.razorpay.payment.entity.OutboxEvent;
import com.codingShuttle.razorpay.payment.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OutboxResultHandler {

    private final OutboxEventRepository outboxEventRepository;
    private final Integer MAX_ATTEMPTS = 3;

    @Transactional
    public void handleEventPublished(OutboxEvent event) {
        event.setStatus(OutboxStatus.PUBLISHED);
        event.setPublishedAt(LocalDateTime.now());
        outboxEventRepository.save(event);

    }

    public void handleEventFailed(OutboxEvent event, String errorMessage) {
        event.setAttempts(event.getAttempts()+1);
        event.setLastError(
                errorMessage.length() < 1000 ? errorMessage : errorMessage.substring(0, 1000));
        if (event.getAttempts() >= MAX_ATTEMPTS){
            event.setStatus(OutboxStatus.FAILED);
        }
        outboxEventRepository.save(event);
    }
}
