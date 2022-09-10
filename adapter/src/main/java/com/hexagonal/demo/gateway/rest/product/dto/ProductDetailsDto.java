package com.hexagonal.demo.gateway.rest.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailsDto {

    private Integer id;
    private String name;
    private String description;
    private Integer originalPrice;
    private DiscountDto discount;
    private List<byte[]> pictureGallery;
}