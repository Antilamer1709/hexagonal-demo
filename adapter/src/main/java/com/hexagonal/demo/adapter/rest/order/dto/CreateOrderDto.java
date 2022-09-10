package com.hexagonal.demo.adapter.rest.order.dto;

import lombok.Data;

@Data
public class CreateOrderDto {

    private Integer productId;
    private Integer amount;
}