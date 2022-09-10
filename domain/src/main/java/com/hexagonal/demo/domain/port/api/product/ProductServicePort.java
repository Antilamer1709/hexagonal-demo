package com.hexagonal.demo.domain.port.api.product;

import com.hexagonal.demo.domain.model.product.ProductDomainModel;

import java.util.List;

/**
 * Primary port is used to manage products
 */
public interface ProductServicePort {

    /**
     * Gets all available products without pagination and filtering
     *
     * @return list of all available products
     */
    List<ProductDomainModel> getAllProducts();

    /**
     * Finds a product by the given id
     *
     * @param id of the product
     * @return the product with the given id
     */
    ProductDomainModel getProductById(Integer id);

    /**
     * Update warehouse availability
     * Deletes given number from available products
     *
     * @param amount how many products are no longer available
     * @param product that has to be updated in the warehouse
     */
    void removeFromWarehouse(Integer amount, ProductDomainModel product);
}