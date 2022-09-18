package com.hexagonal.demo.domain.model.order;

import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDomainModel {

    private Integer orderId;
    private Integer amount;
    private LocalDateTime creationDate;
    private ProductDomainModel product;
}