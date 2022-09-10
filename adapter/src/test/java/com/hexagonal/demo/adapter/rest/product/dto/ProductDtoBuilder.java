package com.hexagonal.demo.adapter.rest.product.dto;

import com.hexagonal.demo.domain.model.product.ProductDomainModelBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.hexagonal.demo.domain.model.product.ProductDomainModelBuilder.MAIN_PICTURE;
import static java.util.stream.IntStream.range;

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

    public List<ProductDto> buildMany(Integer amount) {
        List<ProductDto> result = new ArrayList<>();

        range(1, amount + 1).forEach(index -> {
            var newProduct = new ProductDto();

            newProduct.setId(index);
            newProduct.setName(product.getName() + index);
            newProduct.setOriginalPrice(product.getOriginalPrice() + index * 10);
            newProduct.setMainPicture((MAIN_PICTURE + index).getBytes());

            result.add(newProduct);
        });

        return result;
    }
}