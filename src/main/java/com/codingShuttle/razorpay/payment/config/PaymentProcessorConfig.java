package com.codingShuttle.razorpay.payment.config;

import com.codingShuttle.razorpay.common.enums.PaymentMethod;
import com.codingShuttle.razorpay.payment.processor.PaymentProcessor;
import com.codingShuttle.razorpay.payment.processor.strategy.CardPaymentProcessor;
import com.codingShuttle.razorpay.payment.processor.strategy.NetBankingPaymentProcessor;
import com.codingShuttle.razorpay.payment.processor.strategy.UpiPaymentProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class PaymentProcessorConfig {

    private final CardPaymentProcessor cardPaymentProcessor;
    private final NetBankingPaymentProcessor netBankingPaymentProcessor;
    private final UpiPaymentProcessor upiPaymentProcessor;

    @Bean
    public Map<PaymentMethod, PaymentProcessor> paymentMethodPaymentProcessorMap(){
        return Map.of(
                PaymentMethod.CARD, cardPaymentProcessor,
                PaymentMethod.NETBANKING, netBankingPaymentProcessor,
                PaymentMethod.UPI, upiPaymentProcessor
        );
    }
}
