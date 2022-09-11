package com.hexagonal.demo.adapter.rest.order.mapper;

import com.hexagonal.demo.adapter.rest.order.dto.CreateOrderDto;
import com.hexagonal.demo.adapter.rest.order.dto.OrderDto;
import com.hexagonal.demo.domain.model.order.OrderDomainModel;
import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(imports = ProductDomainModel.class)
public interface OrderRestMapper {

    OrderRestMapper ORDER_REST_MAPPER = getMapper(OrderRestMapper.class);

    @Mapping(target = "productId", source = "product.id")
    OrderDto toDto(OrderDomainModel orderDomainModel);

    List<OrderDto> toDtoList(List<OrderDomainModel> orderDomainModelList);

    @Mapping(target = "product", expression = "java(new ProductDomainModel(createOrderDto.getProductId()))")
    OrderDomainModel toDomainModel(CreateOrderDto createOrderDto);
}