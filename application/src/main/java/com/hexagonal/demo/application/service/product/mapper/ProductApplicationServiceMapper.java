package com.hexagonal.demo.application.service.product.mapper;

import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ProductApplicationServiceMapper {

    ProductApplicationServiceMapper PRODUCT_APPLICATION_SERVICE_MAPPER = getMapper(ProductApplicationServiceMapper.class);

    @Mapping(target = "id", source = "jpaProduct.id")
    @Mapping(target = "name", source = "jpaProduct.name")
    @Mapping(target = "originalPrice", source = "jpaProduct.originalPrice")
    @Mapping(target = "description", source = "jpaProduct.description")
    @Mapping(target = "discount", source = "jpaProduct.discount")
    @Mapping(target = "mainPicture", source = "s3Product.mainPicture")
    @Mapping(target = "pictureGallery", source = "s3Product.pictureGallery")
    @Mapping(target = "availableInWarehouse", source = "warehouseProduct.availableInWarehouse")
    ProductDomainModel toDomainModel(ProductDomainModel jpaProduct, ProductDomainModel s3Product, ProductDomainModel warehouseProduct);

    @Mapping(target = "id", source = "jpaProduct.id")
    @Mapping(target = "name", source = "jpaProduct.name")
    @Mapping(target = "originalPrice", source = "jpaProduct.originalPrice")
    @Mapping(target = "description", source = "jpaProduct.description")
    @Mapping(target = "discount", source = "jpaProduct.discount")
    @Mapping(target = "mainPicture", source = "mainPicture")
    @Mapping(target = "availableInWarehouse", source = "availableInWarehouse")
    ProductDomainModel toDomainModel(ProductDomainModel jpaProduct, byte[] mainPicture, Integer availableInWarehouse);
}