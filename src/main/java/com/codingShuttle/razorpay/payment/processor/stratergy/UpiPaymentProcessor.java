package com.codingShuttle.razorpay.payment.processor.stratergy;

import com.codingShuttle.razorpay.common.util.RandomizerUtil;
import com.codingShuttle.razorpay.payment.processor.PaymentProcessor;
import com.codingShuttle.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.codingShuttle.razorpay.payment.processor.dto.PaymentProcessorResponse;

public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {

        final String VPA_CODE_FAIL = "fail@okaxis";

        String bankCode = request.methodDetails() != null
                ? request.methodDetails().get("vpa").toString()
                : null;

        if(VPA_CODE_FAIL.equals(bankCode)){
            return new PaymentProcessorResponse.Failure("BANK_REJECTED",
                    "Bank rejected the transaction registration.");
        }

        String processorRef = "UPI_PROCESSOR_" + RandomizerUtil.randomBase64(16);

//        String bankRef = "BANK_REF" + RandomizerUtil.randomBase64(16);

        return new PaymentProcessorResponse.Pending(processorRef);
    }
}
