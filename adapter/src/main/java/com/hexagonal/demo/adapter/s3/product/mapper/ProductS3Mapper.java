package com.hexagonal.demo.adapter.s3.product.mapper;

import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ProductS3Mapper {

    ProductS3Mapper PRODUCT_S3_MAPPER = getMapper(ProductS3Mapper.class);

    @Mapping(target = "mainPicture", source = "mainPicture")
    @Mapping(target = "pictureGallery", source = "pictureGallery")
    ProductDomainModel toDomainModel(byte[] mainPicture, List<byte[]> pictureGallery);
}