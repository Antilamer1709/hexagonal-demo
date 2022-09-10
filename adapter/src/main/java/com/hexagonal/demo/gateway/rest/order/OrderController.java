package com.hexagonal.demo.gateway.rest.order;

import com.hexagonal.demo.domain.port.api.order.OrderServicePort;
import com.hexagonal.demo.gateway.rest.order.dto.CreateOrderDto;
import com.hexagonal.demo.gateway.rest.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hexagonal.demo.gateway.rest.order.mapper.OrderRestMapper.ORDER_REST_MAPPER;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServicePort orderServicePort;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return ORDER_REST_MAPPER.toDtoList(orderServicePort.getAllOrders());
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody CreateOrderDto createOrderDto) {
        return ORDER_REST_MAPPER.toDto(orderServicePort.createOrder(ORDER_REST_MAPPER.toDomainModel(createOrderDto)));
    }
}