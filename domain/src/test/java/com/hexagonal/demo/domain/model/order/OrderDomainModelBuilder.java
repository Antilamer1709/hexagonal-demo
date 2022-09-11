package com.hexagonal.demo.domain.model.order;

import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import com.hexagonal.demo.domain.model.product.ProductDomainModelBuilder;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.IntStream.range;

public class OrderDomainModelBuilder {

    private final OrderDomainModel order;

    public OrderDomainModelBuilder() {
        order = new OrderDomainModel();
    }

    public OrderDomainModelBuilder defaultOrder() {
        order.setOrderId(1);
        order.setAmount(1);
        order.setProduct(new ProductDomainModelBuilder().defaultProduct().build());

        return this;
    }

    public OrderDomainModelBuilder withProductId(Integer productId) {
        if (isNull(order.getProduct()))
            order.setProduct(new ProductDomainModel());

        order.getProduct().setId(productId);

        return this;
    }

    public OrderDomainModelBuilder withAmount(Integer amount) {
        order.setAmount(amount);

        return this;
    }

    public OrderDomainModelBuilder withOrderId(Integer orderId) {
        order.setOrderId(orderId);

        return this;
    }

    public OrderDomainModel build() {
        return order;
    }

    public List<OrderDomainModel> buildMany(Integer amount) {
        List<OrderDomainModel> result = new ArrayList<>();

        range(1, amount + 1).forEach(index -> {
            var newOrder = new OrderDomainModel();

            newOrder.setOrderId(index);
            newOrder.setAmount(index);
            newOrder.setProduct(order.getProduct());

            result.add(newOrder);
        });

        return result;
    }
}