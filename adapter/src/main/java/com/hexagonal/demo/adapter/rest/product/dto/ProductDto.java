package com.hexagonal.demo.adapter.rest.product.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Integer id;
    private String name;
    private Integer originalPrice;
    private DiscountDto discount;
    private byte[] mainPicture;
}