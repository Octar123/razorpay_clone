package com.codingShuttle.razorpay.payment.config;

import com.codingShuttle.razorpay.common.enums.PaymentMethod;
import com.codingShuttle.razorpay.payment.gateway.adapter.CardPaymentAdapter;
import com.codingShuttle.razorpay.payment.gateway.adapter.NetBankingAdapter;
import com.codingShuttle.razorpay.payment.gateway.adapter.UpiPaymentAdapter;
import com.codingShuttle.razorpay.payment.processor.PaymentProcessor;
import com.codingShuttle.razorpay.payment.processor.stratergy.CardPaymentProcessor;
import com.codingShuttle.razorpay.payment.processor.stratergy.NetBankingPaymentProcessor;
import com.codingShuttle.razorpay.payment.processor.stratergy.UpiPaymentProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentProcessorConfig {

    @Bean
    public Map<PaymentMethod, PaymentProcessor> paymentMethodPaymentProcessorMap(){
        return Map.of(
                PaymentMethod.CARD, new CardPaymentProcessor(),
                PaymentMethod.NETBANKING, new NetBankingPaymentProcessor(),
                PaymentMethod.UPI, new UpiPaymentProcessor()
        );
    }
}
