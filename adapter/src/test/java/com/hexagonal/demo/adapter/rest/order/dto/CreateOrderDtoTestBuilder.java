package com.hexagonal.demo.adapter.rest.order.dto;

import com.hexagonal.demo.domain.model.order.OrderDomainModelTestBuilder;

public class CreateOrderDtoTestBuilder {

    private final CreateOrderDto createOrder;

    public CreateOrderDtoTestBuilder() {
        createOrder = new CreateOrderDto();
    }

    public CreateOrderDtoTestBuilder defaultOrder() {
        var orderDomainModel = new OrderDomainModelTestBuilder()
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