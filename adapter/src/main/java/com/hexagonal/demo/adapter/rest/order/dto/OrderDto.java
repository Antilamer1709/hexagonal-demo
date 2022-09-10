package com.hexagonal.demo.adapter.rest.order.dto;

import lombok.Data;

@Data
public class OrderDto {

    private Integer orderId;
    private Integer productId;
    private Integer amount;
    // TODO add date when it was created
}