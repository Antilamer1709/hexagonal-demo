package com.hexagonal.demo.application.api.product;

import com.github.database.rider.core.api.dataset.DataSet;
import com.hexagonal.demo.adapter.rest.product.dto.*;
import com.hexagonal.demo.application.AbstractApiTest;
import io.restassured.common.mapper.TypeRef;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductApiTest extends AbstractApiTest {

    private static final String UNDER_TEST = "/products/";

    @Test
    @DataSet(value = "products.xml")
    void shouldGetAllProducts() {
        List<ProductDto> actual = given()
                .when()
                .get(UNDER_TEST)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("size()", equalTo(2))
                .extract()
                .as(new TypeRef<>() {
                });

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(List.of(
                        new ProductDtoTestBuilder()
                                .defaultProduct()
                                .withDiscount(new DiscountDtoTestBuilder().defaultDiscount().build())
                                .build(),
                        new ProductDtoTestBuilder()
                                .defaultProduct()
                                .withId(TEST_SECOND_ID)
                                .withName(TEST_SECOND_PRODUCT_NAME)
                                .withOriginalPrice(TEST_SECOND_ORIGINAL_PRICE)
                                .build()
                ));
    }

    @Test
    @DataSet(value = "products.xml")
    void shouldGetProductDetails() {
        ProductDetailsDto actual = given()
                .when()
                .get(UNDER_TEST + 1)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract()
                .as(ProductDetailsDto.class);

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(
                        new ProductDetailsDtoTestBuilder()
                                .defaultProduct()
                                .withDiscount(new DiscountDtoTestBuilder().defaultDiscount().build())
                                .build()
                );
    }
}
