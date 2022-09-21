package com.hexagonal.demo.adapter.soap.order.mapper;

import com.hexagonal.demo.domain.model.order.OrderDomainModel;
import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import soap.hexagonal.demo.CreateOrderRequest;
import soap.hexagonal.demo.CreateOrderResponse;
import soap.hexagonal.demo.Order;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(imports = ProductDomainModel.class)
public interface OrderSoapMapper {

    OrderSoapMapper ORDER_SOAP_MAPPER = getMapper(OrderSoapMapper.class);

    @Mapping(target = "product", expression = "java(new ProductDomainModel(createOrderRequest.getProductId()))")
    OrderDomainModel toDomainModel(CreateOrderRequest createOrderRequest);

    @Mapping(target = "productId", source = "product.id")
    Order toOrder(OrderDomainModel orderDomainModel);

    @Mapping(target = "order", expression = "java(toOrder(orderDomainModel))")
    CreateOrderResponse toCreateOrderResponse(OrderDomainModel orderDomainModel);
}