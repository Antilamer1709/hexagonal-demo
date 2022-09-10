package com.hexagonal.demo.adapter.rest.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailsDto {

    private Integer id;
    private String name;
    private String description;
    private Integer originalPrice;
    private DiscountDto discount;
    private Integer availableAmount;
    private List<byte[]> pictureGallery;
}