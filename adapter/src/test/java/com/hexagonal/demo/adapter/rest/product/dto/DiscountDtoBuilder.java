package com.hexagonal.demo.adapter.rest.product.dto;

import com.hexagonal.demo.domain.model.product.DiscountDomainModelBuilder;

public class DiscountDtoBuilder {

    private final DiscountDto discount;

    public DiscountDtoBuilder() {
        discount = new DiscountDto();
    }

    public DiscountDtoBuilder defaultDiscount() {
        var discountDomainModel = new DiscountDomainModelBuilder()
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