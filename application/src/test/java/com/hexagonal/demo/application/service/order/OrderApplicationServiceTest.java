package com.hexagonal.demo.application.service.order;

import com.hexagonal.demo.domain.model.order.OrderDomainModelBuilder;
import com.hexagonal.demo.domain.model.product.ProductDomainModelBuilder;
import com.hexagonal.demo.domain.port.api.product.ProductServicePort;
import com.hexagonal.demo.domain.port.spi.order.OrderJpaPort;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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
    void shouldGetAllProducts() {
        var orders = new OrderDomainModelBuilder().defaultOrder().buildMany(3);
        when(orderJpaPort.getAllOrders()).thenReturn(orders);

        val actual = underTest.getAllOrders();

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(new OrderDomainModelBuilder().defaultOrder().buildMany(3));

        InOrder inOrder = inOrder(orderJpaPort);
        inOrder.verify(orderJpaPort).getAllOrders();
    }

    @Test
    void shouldCreateOrder() {
        var order = new OrderDomainModelBuilder().defaultOrder().withOrderId(null).build();
        var product = new ProductDomainModelBuilder().defaultProduct().build();

        when(productServicePort.getProductById(product.getId())).thenReturn(product);
        when(orderJpaPort.createOrder(order)).thenReturn(new OrderDomainModelBuilder().defaultOrder().build());
        doNothing().when(productServicePort).removeFromWarehouse(order.getAmount(), product);

        val actual = underTest.createOrder(order);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(new OrderDomainModelBuilder().defaultOrder().build());

        InOrder inOrder = inOrder(productServicePort, orderJpaPort);
        inOrder.verify(productServicePort).getProductById(product.getId());
        inOrder.verify(orderJpaPort).createOrder(order);
        inOrder.verify(productServicePort).removeFromWarehouse(order.getAmount(), product);
    }
}
