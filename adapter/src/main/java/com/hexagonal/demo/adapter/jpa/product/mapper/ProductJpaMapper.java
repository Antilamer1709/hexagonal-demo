package com.hexagonal.demo.adapter.jpa.product.mapper;

import com.hexagonal.demo.adapter.jpa.product.entity.ProductEntity;
import com.hexagonal.demo.domain.model.ProductDomainModel;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ProductJpaMapper {

    ProductJpaMapper PRODUCT_JPA_MAPPER = getMapper(ProductJpaMapper.class);

    ProductDomainModel toDomainModel(ProductEntity productEntity);

    List<ProductDomainModel> toDomainModelList(List<ProductEntity> productEntityList);
}