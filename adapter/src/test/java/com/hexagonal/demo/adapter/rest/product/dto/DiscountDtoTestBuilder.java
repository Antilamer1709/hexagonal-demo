package com.hexagonal.demo.adapter.rest.product.dto;

import com.hexagonal.demo.domain.model.product.DiscountDomainModelTestBuilder;

public class DiscountDtoTestBuilder {

    private final DiscountDto discount;

    public DiscountDtoTestBuilder() {
        discount = new DiscountDto();
    }

    public DiscountDtoTestBuilder defaultDiscount() {
        var discountDomainModel = new DiscountDomainModelTestBuilder()
                .defaultDiscount()
                .build();

        discount.setDiscountInPercent(discountDomainModel.getDiscountInPercent());
        discount.setPriceAfterDiscount(discountDomainModel.getPriceAfterDiscount());

        return this;
    }

    public DiscountDto build() {
        return discount;
    }
}