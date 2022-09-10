package com.hexagonal.demo.adapter.sdk.product;

import com.hexagonal.demo.adapter.sdk.SdkConfig.DiscountService;
import com.hexagonal.demo.domain.model.product.DiscountDomainModel;
import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import com.hexagonal.demo.domain.port.spi.product.DiscountSdkPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.hexagonal.demo.adapter.sdk.product.mapper.DiscountSdkMapper.DISCOUNT_SDK_MAPPER;

@Component
@RequiredArgsConstructor
public class DiscountSdkAdapter implements DiscountSdkPort {

    private final DiscountService discountService;

    @Override
    public DiscountDomainModel getDiscount(ProductDomainModel productDomainModel) {
        var discount = discountService.getProductDiscount(productDomainModel.getId());

        return DISCOUNT_SDK_MAPPER.toDomainModel(discount);
    }
}