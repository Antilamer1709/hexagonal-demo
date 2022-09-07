package com.hexagonal.demo.domain.port.spi.product;

import com.hexagonal.demo.domain.model.ProductDomainModel;

public interface ProductS3Port {

     ProductDomainModel getPicture(ProductDomainModel productDomainModel);
}