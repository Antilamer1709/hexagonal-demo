package com.hexagonal.demo.application.service.product;

import com.hexagonal.demo.domain.model.ProductDomainModel;
import com.hexagonal.demo.domain.port.api.product.ProductServicePort;
import com.hexagonal.demo.domain.port.spi.product.ProductJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductApplicationService implements ProductServicePort {

    private final ProductJpaPort productJpaPort;

    @Override
    public List<ProductDomainModel> getAllProducts() {
        return productJpaPort.getAllProducts();
    }

    @Override
    public ProductDomainModel getProductById(Integer id) {
        return productJpaPort.getProductById(id);
    }
}