package com.hexagonal.demo.application.service.product.mapper;

import com.hexagonal.demo.domain.model.product.DiscountDomainModelTestBuilder;
import com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.hexagonal.demo.application.service.product.mapper.ProductApplicationServiceMapper.PRODUCT_APPLICATION_SERVICE_MAPPER;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ProductApplicationServiceMapper.class)
public class ProductApplicationServiceMapperTest {

    @Test
    void shouldMapToDetailsDomainModel() {
        var jpaProduct = new ProductDomainModelTestBuilder()
                .jpaProduct()
                .withDiscount(new DiscountDomainModelTestBuilder().defaultDiscount().build())
                .build();
        var s3Product = new ProductDomainModelTestBuilder().s3Product().build();
        var warehouseProduct = new ProductDomainModelTestBuilder().warehouseProduct().build();

        val actual = PRODUCT_APPLICATION_SERVICE_MAPPER.toDomainModel(jpaProduct, s3Product, warehouseProduct);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDomainModelTestBuilder()
                                .defaultProduct()
                                .withDiscount(new DiscountDomainModelTestBuilder().defaultDiscount().build())
                                .build()
                );
    }

    @Test
    void shouldMapToListDomainModel() {
        var jpaProduct = new ProductDomainModelTestBuilder()
                .jpaProduct()
                .withDiscount(new DiscountDomainModelTestBuilder().defaultDiscount().build())
                .build();
        var s3Product = new ProductDomainModelTestBuilder().s3Product().build();
        var warehouseProduct = new ProductDomainModelTestBuilder().warehouseProduct().build();

        val actual = PRODUCT_APPLICATION_SERVICE_MAPPER.toDomainModel(jpaProduct, s3Product.getMainPicture(), warehouseProduct.getAvailableInWarehouse());

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDomainModelTestBuilder()
                                .defaultProduct()
                                .withDiscount(new DiscountDomainModelTestBuilder().defaultDiscount().build())
                                .withPictureGallery(null)
                                .build()
                );
    }
}
