package com.codingShuttle.razorpay.merchant.api;

import com.codingShuttle.razorpay.common.dto.SettlementBankDetails;
import com.codingShuttle.razorpay.common.dto.WebhookTarget;

import java.util.List;
import java.util.UUID;

public interface MerchantLookupService {

    List<WebhookTarget> getActiveConfigsForEvent(UUID merchantId, String eventType);

    List<UUID> listActiveMerchantIds();

    SettlementBankDetails getSettlementBankDetails(UUID merchantId);
}
