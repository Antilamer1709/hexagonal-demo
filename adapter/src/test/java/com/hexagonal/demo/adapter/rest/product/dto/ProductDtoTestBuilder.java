package com.hexagonal.demo.adapter.rest.product.dto;

import com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder.MAIN_PICTURE;
import static java.util.stream.IntStream.range;

public class ProductDtoTestBuilder {

    private final ProductDto product;

    public ProductDtoTestBuilder() {
        product = new ProductDto();
    }

    public ProductDtoTestBuilder defaultProduct() {
        var productDomainModel = new ProductDomainModelTestBuilder()
                .defaultProduct()
                .build();

        product.setId(productDomainModel.getId());
        product.setName(productDomainModel.getName());
        product.setOriginalPrice(productDomainModel.getOriginalPrice());
        product.setMainPicture(productDomainModel.getMainPicture());

        return this;
    }

    public ProductDtoTestBuilder withDiscount(DiscountDto discountDto) {
        product.setDiscount(discountDto);

        return this;
    }

    public ProductDto build() {
        return product;
    }

    public List<ProductDto> buildMany(Integer amount) {
        List<ProductDto> result = new ArrayList<>();
        result.add(product); // The first element is the one that has been built

        // Next elements (come from second element) are copy of the original with index added to the fields
        range(2, amount + 1).forEach(index -> {
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