package com.hexagonal.demo.adapter.rest.product.mapper;

import com.hexagonal.demo.adapter.rest.product.dto.DiscountDtoTestBuilder;
import com.hexagonal.demo.adapter.rest.product.dto.ProductDetailsDtoTestBuilder;
import com.hexagonal.demo.adapter.rest.product.dto.ProductDtoTestBuilder;
import com.hexagonal.demo.domain.model.product.DiscountDomainModelTestBuilder;
import com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.hexagonal.demo.adapter.rest.product.mapper.ProductRestMapper.PRODUCT_REST_MAPPER;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ProductRestMapper.class)
class ProductRestMapperTest {

    @Test
    void shouldMapToProductDetailsDto() {
        val actual = PRODUCT_REST_MAPPER.toProductDetailsDto(
                new ProductDomainModelTestBuilder()
                        .defaultProduct()
                        .withDiscount(new DiscountDomainModelTestBuilder().defaultDiscount().build())
                        .build()
        );

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDetailsDtoTestBuilder()
                                .defaultProduct()
                                .withDiscount(new DiscountDtoTestBuilder().defaultDiscount().build())
                                .build()
                );
    }

    @Test
    void shouldMapToDto() {
        val actual = PRODUCT_REST_MAPPER.toDto(
                new ProductDomainModelTestBuilder()
                        .defaultProduct()
                        .withDiscount(new DiscountDomainModelTestBuilder().defaultDiscount().build())
                        .build()
        );

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDtoTestBuilder()
                                .defaultProduct()
                                .withDiscount(new DiscountDtoTestBuilder().defaultDiscount().build())
                                .build()
                );
    }

    @Test
    void shouldMapToDtoList() {
        val actual = PRODUCT_REST_MAPPER.toDtoList(
                new ProductDomainModelTestBuilder()
                        .defaultProduct()
                        .buildMany(3)
        );

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDtoTestBuilder()
                                .defaultProduct()
                                .buildMany(3)
                );
    }
}