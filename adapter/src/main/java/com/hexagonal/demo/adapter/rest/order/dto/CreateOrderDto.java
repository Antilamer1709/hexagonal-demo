package com.hexagonal.demo.adapter.rest.order.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateOrderDto {

    @NotNull
    @Positive
    private Integer productId;

    @NotNull
    @Positive
    private Integer amount;
}