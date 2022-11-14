package com.hexagonal.demo.adapter.jpa.order;

import com.github.database.rider.core.api.dataset.DataSet;
import com.hexagonal.demo.adapter.jpa.AbstractJpaAdapterIntegrationTest;
import com.hexagonal.demo.domain.model.order.OrderDomainModelTestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static com.hexagonal.demo.domain.model.order.OrderDomainModelTestBuilder.CREATION_DATE_FIELD;
import static com.hexagonal.demo.domain.model.order.OrderDomainModelTestBuilder.TEST_FIRST_ORDER_CREATION_DATE;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {OrderJpaAdapter.class})
public class OrderJpaAdapterIntegrationTest extends AbstractJpaAdapterIntegrationTest {

    @Autowired
    private OrderJpaAdapter underTest;

    @Test
    @DataSet(value = {"products.xml", "orders.xml"})
    void shouldGetAllOrders() {
        var actual = underTest.getAllOrders();

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new OrderDomainModelTestBuilder()
                                .withOrderId(1)
                                .withAmount(1)
                                .withProductId(1)
                                .withCreationDate(LocalDateTime.parse(TEST_FIRST_ORDER_CREATION_DATE))
                                .buildMany(2)
                );
    }

    @Test
    @DataSet(value = {"products.xml"})
    void shouldCreateOrder() {
        var now = LocalDateTime.now();

        var actual = underTest.createOrder(
                new OrderDomainModelTestBuilder()
                        .withProductId(1)
                        .withAmount(1)
                        .build()
        );

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields(CREATION_DATE_FIELD)
                .isEqualTo(
                        new OrderDomainModelTestBuilder()
                                .withOrderId(1)
                                .withAmount(1)
                                .withProductId(1)
                                .build()
                );
        assertThat(actual.getCreationDate().toLocalDate()).isEqualTo(now.toLocalDate());
        assertThat(actual.getCreationDate().getHour()).isEqualTo(now.getHour());
        assertThat(actual.getCreationDate().getMinute()).isEqualTo(now.getMinute());
    }
}