package com.hexagonal.demo.application.service.order;

import com.hexagonal.demo.domain.model.order.OrderDomainModelTestBuilder;
import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder;
import com.hexagonal.demo.domain.port.api.product.ProductServicePort;
import com.hexagonal.demo.domain.port.spi.order.OrderJpaPort;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.hexagonal.demo.domain.model.order.OrderDomainModelTestBuilder.TEST_CREATION_DATE;
import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderApplicationServiceTest {

    @Mock
    private ProductServicePort productServicePort;

    @Mock
    private OrderJpaPort orderJpaPort;

    @InjectMocks
    private OrderApplicationService underTest;

    @Test
    void shouldGetAllOrders() {
        var orders = new OrderDomainModelTestBuilder()
                .defaultOrder()
                .withCreationDate(LocalDateTime.parse(TEST_CREATION_DATE))
                .buildMany(3);
        when(orderJpaPort.getAllOrders()).thenReturn(orders);

        val actual = underTest.getAllOrders();

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new OrderDomainModelTestBuilder()
                                .defaultOrder()
                                .withCreationDate(LocalDateTime.parse(TEST_CREATION_DATE))
                                .buildMany(3)
                );

        InOrder inOrder = inOrder(orderJpaPort);
        inOrder.verify(orderJpaPort).getAllOrders();
    }

    @ParameterizedTest
    @MethodSource(value = "createOrderArguments")
    void shouldCreateOrder(ProductDomainModel product, Class<? extends Exception> expectedException) {
        var order = new OrderDomainModelTestBuilder().defaultOrder().withOrderId(null).build();
        when(productServicePort.getProductById(product.getId())).thenReturn(product);

        if (nonNull(expectedException)) {
            assertThrows(expectedException, () -> underTest.createOrder(order));
            return;
        }

        when(orderJpaPort.createOrder(order)).thenReturn(
                new OrderDomainModelTestBuilder()
                        .defaultOrder()
                        .withCreationDate(LocalDateTime.parse(TEST_CREATION_DATE))
                        .build()
        );
        doNothing().when(productServicePort).removeFromWarehouse(order.getAmount(), product);

        val actual = underTest.createOrder(order);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new OrderDomainModelTestBuilder()
                                .withCreationDate(LocalDateTime.parse(TEST_CREATION_DATE))
                                .defaultOrder()
                                .build()
                );

        InOrder inOrder = inOrder(productServicePort, orderJpaPort);
        inOrder.verify(productServicePort).getProductById(product.getId());
        inOrder.verify(orderJpaPort).createOrder(order);
        inOrder.verify(productServicePort).removeFromWarehouse(order.getAmount(), product);
    }

    private static Stream<Arguments> createOrderArguments() {
        return Stream.of(
                Arguments.of(new ProductDomainModelTestBuilder().defaultProduct().build(), null),
                Arguments.of(new ProductDomainModelTestBuilder().defaultProduct().withAvailableInWarehouse(0).build(), IllegalArgumentException.class)
        );
    }
}
