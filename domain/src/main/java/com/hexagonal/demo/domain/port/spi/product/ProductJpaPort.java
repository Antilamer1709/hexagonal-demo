package com.hexagonal.demo.domain.port.spi.product;

import com.hexagonal.demo.domain.model.product.ProductDomainModel;

import java.util.Collection;
import java.util.List;

/**
 * Secondary port is used to manage products on persistence level
 */
public interface ProductJpaPort {

    /**
     * Finds a product by the given id
     *
     * @param id of the product
     * @return the product with the given id populated only with Jpa data
     */
    ProductDomainModel getProductById(Integer id);

    /**
     * Gets products by ids without pagination and filtering
     *
     * @param productIds collection of product IDs
     * @return list of all available products populated only with Jpa data
     */
    List<ProductDomainModel> getAllProductByIds(Collection<Integer> productIds);
}