package com.hexagonal.demo.adapter.jpa.product.mapper;

import com.hexagonal.demo.adapter.jpa.product.entity.ProductEntityTestBuilder;
import com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.hexagonal.demo.adapter.jpa.product.mapper.ProductJpaMapper.PRODUCT_JPA_MAPPER;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ProductJpaMapper.class)
class ProductJpaMapperTest {

    @Test
    void shouldMapToDomainModel() {
        val actual = PRODUCT_JPA_MAPPER.toDomainModel(
                new ProductEntityTestBuilder().defaultProduct().build()
        );

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDomainModelTestBuilder().jpaProduct().build()
                );
    }

    @Test
    void shouldMapToDomainModelList() {
        val actual = PRODUCT_JPA_MAPPER.toDomainModelList(
                new ProductEntityTestBuilder().defaultProduct().buildMany(3)
        );

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDomainModelTestBuilder().jpaProduct().buildMany(3)
                );
    }
}