package com.hexagonal.demo.adapter.jpa.product;

import com.hexagonal.demo.adapter.jpa.AbstractAdapterIntegrationTest;
import com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {ProductJpaAdapter.class})
public class ProductJpaAdapterIntegrationTest extends AbstractAdapterIntegrationTest {

    @Autowired
    private ProductJpaAdapter underTest;

    @Test
    void shouldGetProductById() {
        var actual = underTest.getProductById(10001);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDomainModelTestBuilder()
                                .jpaProduct()
                                .withId(10001)
                                .build()
                );
    }

    @Test
    void shouldGetAllProductByIds() {
        var actual = underTest.getAllProductByIds(List.of(10001, 10002));

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDomainModelTestBuilder()
                                .jpaProduct()
                                .withId(10001)
                                .buildMany(2)
                );
    }
}