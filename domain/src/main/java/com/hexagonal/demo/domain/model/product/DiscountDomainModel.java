package com.hexagonal.demo.domain.model.product;

import lombok.Data;

@Data
public class DiscountDomainModel {

    private Integer priceAfterDiscount;
    private Integer discountInPercent;
}