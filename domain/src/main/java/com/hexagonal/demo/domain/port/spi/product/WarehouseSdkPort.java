package com.hexagonal.demo.domain.port.spi.product;

import com.hexagonal.demo.domain.model.product.ProductDomainModel;

import java.util.List;

/**
 * Secondary port is used to manage available products on SDK level
 */
public interface WarehouseSdkPort {

    /**
     * Gets an amount of available product in the warehouse
     *
     * @param productDomainModel product which availability we are checking for
     * @return amount of available product
     */
    ProductDomainModel getAvailableAmount(ProductDomainModel productDomainModel);


    /**
     * Gets all available products with their quantities
     *
     * @return available products
     */
    List<ProductDomainModel> getAvailableProducts();

    /**
     * Update warehouse availability
     * Deletes given number from available products
     *
     * @param amount how many products are no longer available
     * @param product that has to be updated in the warehouse
     */
    void remove(Integer amount, ProductDomainModel product);
}