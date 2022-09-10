package com.hexagonal.demo.adapter.jpa.product;

import com.hexagonal.demo.adapter.jpa.product.repository.ProductRepository;
import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import com.hexagonal.demo.domain.port.spi.product.ProductJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static com.hexagonal.demo.adapter.jpa.product.mapper.ProductJpaMapper.PRODUCT_JPA_MAPPER;

@Component
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductJpaPort {

    private final ProductRepository productRepository;

    @Override
    public ProductDomainModel getProductById(Integer id) {
        return PRODUCT_JPA_MAPPER.toDomainModel(productRepository.findById(id).orElse(null));
    }

    @Override
    public List<ProductDomainModel> getAllProductByIds(Collection<Integer> productIds) {
        return PRODUCT_JPA_MAPPER.toDomainModelList(productRepository.findAllById(productIds));
    }
}