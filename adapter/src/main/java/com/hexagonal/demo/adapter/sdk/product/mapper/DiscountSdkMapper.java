package com.hexagonal.demo.adapter.sdk.product.mapper;

import com.hexagonal.demo.adapter.sdk.SdkConfig.Discount;
import com.hexagonal.demo.domain.model.product.DiscountDomainModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface DiscountSdkMapper {

    DiscountSdkMapper DISCOUNT_SDK_MAPPER = getMapper(DiscountSdkMapper.class);

    @Mapping(target = "discountInPercent", source = "discount")
    DiscountDomainModel toDomainModel(Discount discount);
}