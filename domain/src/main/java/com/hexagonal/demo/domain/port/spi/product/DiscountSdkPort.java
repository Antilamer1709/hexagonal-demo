package com.hexagonal.demo.domain.port.spi.product;

import com.hexagonal.demo.domain.model.product.DiscountDomainModel;
import com.hexagonal.demo.domain.model.product.ProductDomainModel;

/**
 * Secondary port is used to manage discounts on SDK level
 */
public interface DiscountSdkPort {

    /**
     * Gets a discount for the given product.
     * Discount is in the percent form, so the actual sum has to be calculated
     *
     * @param productDomainModel product which discount we are looking for
     * @return discount object
     */
    DiscountDomainModel getDiscount(ProductDomainModel productDomainModel);
}