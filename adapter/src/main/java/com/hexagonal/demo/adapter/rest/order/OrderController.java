package com.hexagonal.demo.adapter.rest.order;

import com.hexagonal.demo.adapter.rest.order.dto.CreateOrderDto;
import com.hexagonal.demo.adapter.rest.order.dto.OrderDto;
import com.hexagonal.demo.domain.port.api.order.OrderServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.hexagonal.demo.adapter.rest.order.mapper.OrderRestMapper.ORDER_REST_MAPPER;

@Tag(name = "order", description = "Order operations")
@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServicePort orderServicePort;

    @Operation(summary = "Get all orders")
    @GetMapping
    public List<OrderDto> getAllOrders() {
        return ORDER_REST_MAPPER.toDtoList(orderServicePort.getAllOrders());
    }

    @Operation(summary = "Create a new order")
    @PostMapping
    public OrderDto createOrder(@RequestBody @Valid CreateOrderDto createOrderDto) {
        return ORDER_REST_MAPPER.toDto(orderServicePort.createOrder(ORDER_REST_MAPPER.toDomainModel(createOrderDto)));
    }
}