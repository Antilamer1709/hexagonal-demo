package com.hexagonal.demo.domain.model.product;

import org.junit.jupiter.api.Test;

import static com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder.TEST_AVAILABLE_IN_WAREHOUSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void shouldApplyNullDiscount() {
        var product = new ProductDomainModelTestBuilder().jpaProduct().withOriginalPrice(2500).build();

        product.applyDiscount(null);

        assertThat(product)
                .usingRecursiveComparison()
                .isEqualTo(
                new ProductDomainModelTestBuilder()
                        .jpaProduct()
                        .withOriginalPrice(2500)
                        .build()
        );
        assertThat(product.getDiscount()).isNull();
    }

    @Test
    void shouldSetAvailableInWarehouse() {
        var product = new ProductDomainModelTestBuilder().jpaProduct().build();

        product.setAvailableInWarehouse(TEST_AVAILABLE_IN_WAREHOUSE);

        assertThat(product)
                .usingRecursiveComparison()
                .isEqualTo(
                new ProductDomainModelTestBuilder()
                        .jpaProduct()
                        .warehouseProduct()
                        .build()
        );
    }

    @Test
    void shouldNotSetNegativeAvailableInWarehouse() {
        var product = new ProductDomainModelTestBuilder().jpaProduct().build();

        assertThrows(IllegalArgumentException.class, () -> product.setAvailableInWarehouse(-1));
    }
}