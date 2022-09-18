package com.hexagonal.demo.domain.model.order;

import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.IntStream.range;

public class OrderDomainModelTestBuilder {

    public static final String CREATION_DATE_FIELD = "creationDate";
    public static final String TEST_CREATION_DATE = "2022-01-01T12:00:00";

    private final OrderDomainModel order;

    public OrderDomainModelTestBuilder() {
        order = new OrderDomainModel();
    }

    public OrderDomainModelTestBuilder defaultOrder() {
        order.setOrderId(1);
        order.setAmount(1);
        order.setProduct(new ProductDomainModelTestBuilder().defaultProduct().build());

        return this;
    }

    public OrderDomainModelTestBuilder withProductId(Integer productId) {
        if (isNull(order.getProduct()))
            order.setProduct(new ProductDomainModel());

        order.getProduct().setId(productId);

        return this;
    }

    public OrderDomainModelTestBuilder withAmount(Integer amount) {
        order.setAmount(amount);

        return this;
    }

    public OrderDomainModelTestBuilder withOrderId(Integer orderId) {
        order.setOrderId(orderId);

        return this;
    }

    public OrderDomainModelTestBuilder withCreationDate(LocalDateTime creationDate) {
        order.setCreationDate(creationDate);

        return this;
    }

    public OrderDomainModel build() {
        return order;
    }

    public List<OrderDomainModel> buildMany(Integer amount) {
        List<OrderDomainModel> result = new ArrayList<>();
        result.add(order); // The first element is the one that has been built

        // Next elements (come from second element) are copy of the original with index added to the fields
        range(2, amount + 1).forEach(index -> {
            var newOrder = new OrderDomainModel();

            newOrder.setAmount(index);
            newOrder.setProduct(order.getProduct());

            if (nonNull(order.getOrderId()))
                newOrder.setOrderId(order.getOrderId() + index - 1);
            if (nonNull(order.getCreationDate()))
                newOrder.setCreationDate(order.getCreationDate().plusDays(index - 1));

            result.add(newOrder);
        });

        return result;
    }
}