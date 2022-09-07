package com.hexagonal.demo.domain.model;

import lombok.Data;

@Data
public class ProductDomainModel {

    private Integer id;
    private String name;
    private byte[] picture;
}