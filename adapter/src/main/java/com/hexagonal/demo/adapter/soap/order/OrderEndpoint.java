package com.hexagonal.demo.adapter.soap.order;

import com.hexagonal.demo.domain.port.api.order.OrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.hexagonal.demo.CreateOrderRequest;
import soap.hexagonal.demo.CreateOrderResponse;

import static com.hexagonal.demo.adapter.soap.WebServiceConfig.NAMESPACE_URI;
import static com.hexagonal.demo.adapter.soap.order.mapper.OrderSoapMapper.ORDER_SOAP_MAPPER;

@Endpoint
@RequiredArgsConstructor
public class OrderEndpoint {

    private final OrderServicePort orderServicePort;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createOrderRequest")
    @ResponsePayload
    public CreateOrderResponse createOrder(@RequestPayload CreateOrderRequest request) {
        var orderDomainModel = orderServicePort.createOrder(ORDER_SOAP_MAPPER.toDomainModel(request));

        return ORDER_SOAP_MAPPER.toCreateOrderResponse(orderDomainModel);
    }
}