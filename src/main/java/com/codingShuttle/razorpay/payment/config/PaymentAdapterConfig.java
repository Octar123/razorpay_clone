package com.codingShuttle.razorpay.payment.config;

import com.codingShuttle.razorpay.common.enums.PaymentMethod;
import com.codingShuttle.razorpay.payment.gateway.PaymentAdapter;
import com.codingShuttle.razorpay.payment.gateway.adapter.CardPaymentAdapter;
import com.codingShuttle.razorpay.payment.gateway.adapter.NetBankingAdapter;
import com.codingShuttle.razorpay.payment.gateway.adapter.UpiPaymentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentAdapterConfig {

    @Bean
    public Map<PaymentMethod, PaymentAdapter> paymentMethodPaymentAdapterMap(){
        return Map.of(
                PaymentMethod.CARD, new CardPaymentAdapter(),
                PaymentMethod.NETBANKING, new NetBankingAdapter(),
                PaymentMethod.UPI, new UpiPaymentAdapter()
        );
    }
}
