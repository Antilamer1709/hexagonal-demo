package com.hexagonal.demo.application.api.order;

import com.github.database.rider.core.api.dataset.DataSet;
import com.hexagonal.demo.adapter.rest.order.dto.OrderDto;
import com.hexagonal.demo.adapter.rest.order.dto.OrderDtoTestBuilder;
import com.hexagonal.demo.application.AbstractApiTest;
import io.restassured.common.mapper.TypeRef;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;

import static com.hexagonal.demo.domain.model.order.OrderDomainModelTestBuilder.*;
import static com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder.TEST_FIRST_ID;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OrderApiTest extends AbstractApiTest {

    private static final String UNDER_TEST = "/orders/";

    @Test
    @DataSet(value = {"products.xml", "orders.xml"})
    void shouldFindAllOrders() {
        List<OrderDto> actual = given()
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
                        new OrderDtoTestBuilder()
                                .defaultOrder()
                                .withCreationDate(LocalDateTime.parse(TEST_FIRST_ORDER_CREATION_DATE))
                                .build(),
                        new OrderDtoTestBuilder()
                                .withOrderId(TEST_SECOND_ORDER_ID)
                                .withProductId(TEST_FIRST_ID)
                                .withAmount(TEST_SECOND_ORDER_AMOUNT)
                                .withCreationDate(LocalDateTime.parse(TEST_SECOND_ORDER_CREATION_DATE))
                                .build()
                ));
    }
}