package com.hexagonal.demo.adapter.rest.product.dto;

import com.hexagonal.demo.domain.model.product.ProductDomainModelBuilder;

public class ProductDetailsDtoBuilder {

    private final ProductDetailsDto product;

    public ProductDetailsDtoBuilder() {
        product = new ProductDetailsDto();
    }

    public ProductDetailsDtoBuilder defaultProduct() {
        var productDomainModel = new ProductDomainModelBuilder()
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

    public ProductDetailsDtoBuilder withDiscount(DiscountDto discountDto) {
        product.setDiscount(discountDto);

        return this;
    }

    public ProductDetailsDto build() {
        return product;
    }
}