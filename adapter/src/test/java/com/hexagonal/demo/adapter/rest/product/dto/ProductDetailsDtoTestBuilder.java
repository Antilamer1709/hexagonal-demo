package com.hexagonal.demo.adapter.rest.product.dto;

import com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder;

public class ProductDetailsDtoTestBuilder {

    private final ProductDetailsDto product;

    public ProductDetailsDtoTestBuilder() {
        product = new ProductDetailsDto();
    }

    public ProductDetailsDtoTestBuilder defaultProduct() {
        var productDomainModel = new ProductDomainModelTestBuilder()
                .defaultProduct()
                .build();

        product.setId(productDomainModel.getId());
        product.setName(productDomainModel.getName());
        product.setDescription(productDomainModel.getDescription());
        product.setOriginalPrice(productDomainModel.getOriginalPrice());
        product.setAvailableAmount(productDomainModel.getAvailableInWarehouse());
        product.setPictureGallery(productDomainModel.getPictureGallery());

        return this;
    }

    public ProductDetailsDtoTestBuilder withDiscount(DiscountDto discountDto) {
        product.setDiscount(discountDto);

        return this;
    }

    public ProductDetailsDto build() {
        return product;
    }
}