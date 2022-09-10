package com.hexagonal.demo.domain.model.order;

import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import lombok.Data;

@Data
public class OrderDomainModel {

    private Integer orderId;
    private Integer amount;
    private ProductDomainModel product;
}