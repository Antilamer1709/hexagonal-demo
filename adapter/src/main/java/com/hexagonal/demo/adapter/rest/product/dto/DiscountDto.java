package com.hexagonal.demo.adapter.rest.product.dto;

import lombok.Data;

@Data
public class DiscountDto {

    private Integer priceAfterDiscount;
    private Integer discountInPercent;
}