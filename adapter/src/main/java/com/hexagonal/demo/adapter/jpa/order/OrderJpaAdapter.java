package com.hexagonal.demo.adapter.jpa.order;

import com.hexagonal.demo.adapter.jpa.order.repository.OrderRepository;
import com.hexagonal.demo.domain.model.order.OrderDomainModel;
import com.hexagonal.demo.domain.port.spi.order.OrderJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.hexagonal.demo.adapter.jpa.order.mapper.OrderJpaMapper.ORDER_JPA_MAPPER;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderJpaPort {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDomainModel> getAllOrders() {
        return ORDER_JPA_MAPPER.toDomainModelList(orderRepository.findAll());
    }

    @Override
    public OrderDomainModel createOrder(OrderDomainModel order) {
        var createdOrder = orderRepository.save(ORDER_JPA_MAPPER.toEntity(order));

        return ORDER_JPA_MAPPER.toDomainModel(createdOrder);
    }
}