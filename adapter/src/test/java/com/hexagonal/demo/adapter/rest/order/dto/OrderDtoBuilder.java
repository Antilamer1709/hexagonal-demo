package com.hexagonal.demo.adapter.rest.order.dto;

import com.hexagonal.demo.domain.model.order.OrderDomainModelBuilder;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;

public class OrderDtoBuilder {

    private final OrderDto order;

    public OrderDtoBuilder() {
        order = new OrderDto();
    }

    public OrderDtoBuilder defaultOrder() {
        var orderDomainModel = new OrderDomainModelBuilder()
                .defaultOrder()
                .build();

        order.setOrderId(orderDomainModel.getOrderId());
        order.setAmount(orderDomainModel.getAmount());
        order.setProductId(orderDomainModel.getProduct().getId());

        return this;
    }

    public OrderDto build() {
        return order;
    }

    public List<OrderDto> buildMany(Integer amount) {
        List<OrderDto> result = new ArrayList<>();

        range(1, amount + 1).forEach(index -> {
            var newOrder = new OrderDto();

            newOrder.setOrderId(index);
            newOrder.setAmount(index);
            newOrder.setProductId(order.getProductId());

            result.add(newOrder);
        });

        return result;
    }
}