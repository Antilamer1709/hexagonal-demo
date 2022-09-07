package com.hexagonal.demo.application.service.product.mapper;

import com.hexagonal.demo.domain.model.ProductDomainModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ProductApplicationServiceMapper {

    ProductApplicationServiceMapper PRODUCT_APPLICATION_SERVICE_MAPPER = getMapper(ProductApplicationServiceMapper.class);

    @Mapping(target = "id", source = "jpaProduct.id")
    @Mapping(target = "name", source = "jpaProduct.name")
    @Mapping(target = "picture", source = "s3Product.picture")
    ProductDomainModel toDomainModel(ProductDomainModel jpaProduct, ProductDomainModel s3Product);
}