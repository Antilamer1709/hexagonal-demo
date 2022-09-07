package com.hexagonal.demo.domain.port.spi.product;

import com.hexagonal.demo.domain.model.ProductDomainModel;

import java.util.List;

public interface ProductJpaPort {

    /**
     * Gets all available products without pagination and filtering
     * @return list of all available products
     */
    List<ProductDomainModel> getAllProducts();

    /**
     * Finds a product by the given id
     * @param id of the product
     * @return the product with the given id
     */
    ProductDomainModel getProductById(Integer id);
}