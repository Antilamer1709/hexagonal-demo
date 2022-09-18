package com.hexagonal.demo.adapter.jpa.product.entity;

import com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;

public class ProductEntityTestBuilder {

    private final ProductEntity product;

    public ProductEntityTestBuilder() {
        product = new ProductEntity();
    }

    public ProductEntityTestBuilder defaultProduct() {
        var productDomainModel = new ProductDomainModelTestBuilder()
                .defaultProduct()
                .build();

        product.setId(productDomainModel.getId());
        product.setName(productDomainModel.getName());
        product.setPrice(productDomainModel.getOriginalPrice());
        product.setDescription(productDomainModel.getDescription());

        return this;
    }

    public ProductEntity build() {
        return product;
    }

    public List<ProductEntity> buildMany(Integer amount) {
        List<ProductEntity> result = new ArrayList<>();
        result.add(product); // The first element is the one that has been built

        // Next elements (come from second element) are copy of the original with index added to the fields
        range(2, amount + 1).forEach(index -> {
            var newProduct = new ProductEntity();

            newProduct.setId(index);
            newProduct.setName(product.getName() + index);
            newProduct.setPrice(product.getPrice() + index * 10);
            newProduct.setDescription(product.getDescription() + index);

            result.add(newProduct);
        });

        return result;
    }
}