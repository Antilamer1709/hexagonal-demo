package com.hexagonal.demo.adapter.rest.order.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {

    private Integer orderId;
    private Integer productId;
    private Integer amount;
    private LocalDateTime creationDate;
}