package com.hexagonal.demo.domain.model.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDomainModelTest {

    @Test
    void shouldApplyDiscount() {
        var product = new ProductDomainModelTestBuilder().jpaProduct().withOriginalPrice(2500).build();
        var discount = new DiscountDomainModelTestBuilder().defaultDiscount().withDiscountInPercent(50).build();

        product.applyDiscount(discount);

        assertThat(product)
                .usingRecursiveComparison()
                .isEqualTo(
                new ProductDomainModelTestBuilder()
                        .jpaProduct()
                        .withOriginalPrice(2500)
                        .withDiscount(new DiscountDomainModelTestBuilder().defaultDiscount().withDiscountInPercent(50).build())
                        .build()
        );
        assertThat(product.getDiscount().getPriceAfterDiscount()).isEqualTo(1250);
    }
}