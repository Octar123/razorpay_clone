package com.codingShuttle.razorpay.merchant.mapper;


import com.codingShuttle.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.codingShuttle.razorpay.merchant.dto.response.MerchantResponse;
import com.codingShuttle.razorpay.merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantMapper {

    Merchant toEntityFromSignUpRequest(MerchantSignupRequest request);

    @Mapping(target = "merchantStatus", source = "status")
    MerchantResponse toResponse(Merchant merchant);
}
