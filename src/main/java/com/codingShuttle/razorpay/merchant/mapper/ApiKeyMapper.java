package com.codingShuttle.razorpay.merchant.mapper;


import com.codingShuttle.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.codingShuttle.razorpay.merchant.dto.response.ApiKeyResponse;
import com.codingShuttle.razorpay.merchant.entity.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyMapper {

//    @Mapping(target = "keySecret", source = "keySecretHash")
//    ApiKeyCreateResponse toCreateResponse(ApiKey apiKey);

    List<ApiKeyResponse> toResponseList(List<ApiKey> apiKeyList);
}
