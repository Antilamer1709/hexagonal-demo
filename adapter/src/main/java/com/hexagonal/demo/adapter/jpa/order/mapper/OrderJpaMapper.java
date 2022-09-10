package com.hexagonal.demo.adapter.jpa.order.mapper;

import com.hexagonal.demo.adapter.jpa.order.entity.OrderEntity;
import com.hexagonal.demo.domain.model.order.OrderDomainModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface OrderJpaMapper {

    OrderJpaMapper ORDER_JPA_MAPPER = getMapper(OrderJpaMapper.class);

    OrderEntity toEntity(OrderDomainModel orderDomainModel);

    @Mapping(target = "orderId", source = "id")
    OrderDomainModel toDomainModel(OrderEntity orderEntity);

    List<OrderDomainModel> toDomainModelList(List<OrderEntity> orderDomainModelList);
}