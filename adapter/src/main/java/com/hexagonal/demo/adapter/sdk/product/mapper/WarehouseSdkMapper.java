package com.hexagonal.demo.adapter.sdk.product.mapper;

import com.hexagonal.demo.adapter.sdk.SdkConfig.Product;
import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface WarehouseSdkMapper {

    WarehouseSdkMapper WAREHOUSE_SDK_MAPPER = getMapper(WarehouseSdkMapper.class);

    @Mapping(target = "id", source = "productId")
    ProductDomainModel toDomainModel(Product product);

    List<ProductDomainModel> toDomainModelList(List<Product> products);
}