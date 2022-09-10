package com.hexagonal.demo.gateway.rest.order.dto;

import lombok.Data;

@Data
public class OrderDto {

    private Integer orderId;
    private Integer productId;
    private Integer amount;
}