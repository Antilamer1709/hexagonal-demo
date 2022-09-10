package com.hexagonal.demo.adapter.sdk.product;

import com.hexagonal.demo.adapter.sdk.SdkConfig.WarehouseService;
import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import com.hexagonal.demo.domain.port.spi.product.WarehouseSdkPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.hexagonal.demo.adapter.sdk.product.mapper.WarehouseSdkMapper.WAREHOUSE_SDK_MAPPER;

@Component
@RequiredArgsConstructor
public class WarehouseSdkAdapter implements WarehouseSdkPort {

    private final WarehouseService warehouseService;

    @Override
    public ProductDomainModel getAvailableAmount(ProductDomainModel productDomainModel) {
        return WAREHOUSE_SDK_MAPPER.toDomainModel(warehouseService.getProductAvailability(productDomainModel.getId()));
    }

    @Override
    public List<ProductDomainModel> getAvailableProducts() {
        return WAREHOUSE_SDK_MAPPER.toDomainModelList(warehouseService.getAllAvailableProducts());
    }

    @Override
    public void remove(Integer amount, ProductDomainModel product) {
        warehouseService.remove(amount, product);
    }
}