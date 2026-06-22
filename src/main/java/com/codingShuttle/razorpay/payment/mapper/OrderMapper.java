package com.codingShuttle.razorpay.payment.mapper;

import com.codingShuttle.razorpay.payment.dto.response.OrderResponse;
import com.codingShuttle.razorpay.payment.entity.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    @Mapping(target = "status", source = "orderStatus")
    OrderResponse toResponse(OrderRecord orderRecord);
}
