package com.hexagonal.demo.adapter.rest.product.mapper;

import com.hexagonal.demo.adapter.rest.product.dto.DiscountDtoBuilder;
import com.hexagonal.demo.adapter.rest.product.dto.ProductDetailsDtoBuilder;
import com.hexagonal.demo.adapter.rest.product.dto.ProductDtoBuilder;
import com.hexagonal.demo.domain.model.product.DiscountDomainModelBuilder;
import com.hexagonal.demo.domain.model.product.ProductDomainModelBuilder;
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
                new ProductDomainModelBuilder()
                        .defaultProduct()
                        .withDiscount(new DiscountDomainModelBuilder().defaultDiscount().build())
                        .build()
        );

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDetailsDtoBuilder()
                                .defaultProduct().withDiscount(new DiscountDtoBuilder().defaultDiscount().build()).build()
                );
    }

    @Test
    void shouldMapToDto() {
        val actual = PRODUCT_REST_MAPPER.toDto(
                new ProductDomainModelBuilder()
                        .defaultProduct()
                        .withDiscount(new DiscountDomainModelBuilder().defaultDiscount().build())
                        .build()
        );

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDtoBuilder()
                                .defaultProduct()
                                .withDiscount(new DiscountDtoBuilder().defaultDiscount().build())
                                .build()
                );
    }

    @Test
    void shouldMapToDtoList() {
        val actual = PRODUCT_REST_MAPPER.toDtoList(
                new ProductDomainModelBuilder()
                        .defaultProduct()
                        .buildMany(3)
        );

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDtoBuilder()
                                .defaultProduct()
                                .buildMany(3)
                );
    }
}