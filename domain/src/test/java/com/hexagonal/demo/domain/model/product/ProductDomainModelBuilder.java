package com.hexagonal.demo.domain.model.product;

import java.util.List;

public class ProductDomainModelBuilder {

    private final ProductDomainModel product;

    public ProductDomainModelBuilder() {
        product = new ProductDomainModel();
    }

    public ProductDomainModelBuilder defaultProduct() {
        product.setId(1);
        product.setName("Test product name");
        product.setOriginalPrice(100);
        product.setAvailableInWarehouse(5);
        product.setDescription("Test product description");
        product.setMainPicture("Main picture".getBytes());
        product.setPictureGallery(List.of(
                "First gallery picture".getBytes(),
                "Second gallery picture".getBytes()
        ));

        return this;
    }

    public ProductDomainModel build() {
        return product;
    }
}