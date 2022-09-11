package com.hexagonal.demo.adapter.rest.order.dto;

import com.hexagonal.demo.domain.model.order.OrderDomainModelBuilder;

public class CreateOrderDtoBuilder {

    private final CreateOrderDto createOrder;

    public CreateOrderDtoBuilder() {
        createOrder = new CreateOrderDto();
    }

    public CreateOrderDtoBuilder defaultOrder() {
        var orderDomainModel = new OrderDomainModelBuilder()
                .defaultOrder()
                .build();

        createOrder.setAmount(orderDomainModel.getAmount());
        createOrder.setProductId(orderDomainModel.getProduct().getId());

        return this;
    }

    public CreateOrderDto build() {
        return createOrder;
    }
}