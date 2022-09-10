package com.hexagonal.demo.adapter.rest.product.dto;

import com.hexagonal.demo.domain.model.product.ProductDomainModelBuilder;

public class ProductDtoBuilder {

    private final ProductDto product;

    public ProductDtoBuilder() {
        product = new ProductDto();
    }

    public ProductDtoBuilder defaultProduct() {
        var productDomainModel = new ProductDomainModelBuilder()
                .defaultProduct()
                .build();

        product.setId(productDomainModel.getId());
        product.setName(productDomainModel.getName());
        product.setOriginalPrice(productDomainModel.getOriginalPrice());
        product.setMainPicture(productDomainModel.getMainPicture());

        return this;
    }

    public ProductDto build() {
        return product;
    }
}