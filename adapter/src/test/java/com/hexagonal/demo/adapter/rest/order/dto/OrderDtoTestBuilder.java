package com.hexagonal.demo.adapter.rest.order.dto;

import com.hexagonal.demo.domain.model.order.OrderDomainModelTestBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.stream.IntStream.range;

public class OrderDtoTestBuilder {

    private final OrderDto order;

    public OrderDtoTestBuilder() {
        order = new OrderDto();
    }

    public OrderDtoTestBuilder defaultOrder() {
        var orderDomainModel = new OrderDomainModelTestBuilder()
                .defaultOrder()
                .build();

        order.setOrderId(orderDomainModel.getOrderId());
        order.setAmount(orderDomainModel.getAmount());
        order.setProductId(orderDomainModel.getProduct().getId());

        return this;
    }

    public OrderDtoTestBuilder withCreationDate(LocalDateTime creationDate) {
        order.setCreationDate(creationDate);

        return this;
    }

    public OrderDto build() {
        return order;
    }

    public List<OrderDto> buildMany(Integer amount) {
        List<OrderDto> result = new ArrayList<>();
        result.add(order); // The first element is the one that has been built

        // Next elements (come from second element) are copy of the original with index added to the fields
        range(2, amount + 1).forEach(index -> {
            var newOrder = new OrderDto();

            newOrder.setOrderId(index);
            newOrder.setAmount(index);
            newOrder.setProductId(order.getProductId());
            if (nonNull(order.getCreationDate()))
                newOrder.setCreationDate(order.getCreationDate().plusDays(index - 1));

            result.add(newOrder);
        });

        return result;
    }
}