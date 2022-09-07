package com.hexagonal.demo.application.service.product;

import com.hexagonal.demo.domain.model.ProductDomainModel;
import com.hexagonal.demo.domain.port.api.product.ProductServicePort;
import com.hexagonal.demo.domain.port.spi.product.ProductJpaPort;
import com.hexagonal.demo.domain.port.spi.product.ProductS3Port;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hexagonal.demo.application.service.product.mapper.ProductApplicationServiceMapper.PRODUCT_APPLICATION_SERVICE_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductApplicationService implements ProductServicePort {

    private final ProductS3Port productS3Port;
    private final ProductJpaPort productJpaPort;

    @Override
    public List<ProductDomainModel> getAllProducts() {
        return productJpaPort.getAllProducts().stream()
                .map(jpaProduct -> {
                    var s3Product = productS3Port.getPicture(jpaProduct);
                    return PRODUCT_APPLICATION_SERVICE_MAPPER.toDomainModel(jpaProduct, s3Product);
                })
                .toList();
    }

    @Override
    public ProductDomainModel getProductById(Integer id) {
        var jpaProduct = productJpaPort.getProductById(id);
        var s3Product = productS3Port.getPicture(jpaProduct);

        return PRODUCT_APPLICATION_SERVICE_MAPPER.toDomainModel(jpaProduct, s3Product);
    }
}