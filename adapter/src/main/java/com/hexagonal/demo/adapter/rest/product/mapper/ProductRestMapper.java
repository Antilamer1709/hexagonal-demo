package com.hexagonal.demo.adapter.rest.product.mapper;

import com.hexagonal.demo.adapter.rest.product.dto.ProductDto;
import com.hexagonal.demo.domain.model.ProductDomainModel;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ProductRestMapper {

    ProductRestMapper PRODUCT_REST_MAPPER = getMapper(ProductRestMapper.class);

    ProductDto toDto(ProductDomainModel productDomainModel);

    List<ProductDto> toDtoList(List<ProductDomainModel> productDomainModelList);
}