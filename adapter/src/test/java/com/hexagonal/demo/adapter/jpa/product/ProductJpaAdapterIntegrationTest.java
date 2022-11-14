package com.hexagonal.demo.adapter.jpa.product;

import com.github.database.rider.core.api.dataset.DataSet;
import com.hexagonal.demo.adapter.jpa.AbstractJpaAdapterIntegrationTest;
import com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {ProductJpaAdapter.class})
public class ProductJpaAdapterIntegrationTest extends AbstractJpaAdapterIntegrationTest {

    @Autowired
    private ProductJpaAdapter underTest;

    @Test
    @DataSet(value = {"products.xml"})
    void shouldGetProductById() {
        var actual = underTest.getProductById(1);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDomainModelTestBuilder()
                                .jpaProduct()
                                .withId(1)
                                .build()
                );
    }

    @Test
    @DataSet(value = {"products.xml"})
    void shouldGetAllProductByIds() {
        var actual = underTest.getAllProductByIds(List.of(1, 2));

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDomainModelTestBuilder()
                                .jpaProduct()
                                .withId(1)
                                .buildMany(2)
                );
    }
}