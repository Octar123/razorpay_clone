package com.codingShuttle.razorpay.operations.settlement;

import com.codingShuttle.razorpay.common.entity.Money;
import com.codingShuttle.razorpay.operations.settlement.dto.BankTransferResult;

import java.util.UUID;

public interface BankTransferProcessor {

    BankTransferResult initiate(UUID settlementId, UUID merchantId, Money amount,
                                String bankAccount, String ifsc);
}
