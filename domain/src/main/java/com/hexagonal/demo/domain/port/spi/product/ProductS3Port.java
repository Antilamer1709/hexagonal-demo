package com.hexagonal.demo.domain.port.spi.product;

import com.hexagonal.demo.domain.model.product.ProductDomainModel;

/**
 * Secondary port is used to manage products on S3 level
 */
public interface ProductS3Port {

    /**
     * Downloads pictures for the provided product
     *
     * @param productDomainModel product what pictures we are looking for
     * @return ProductDomainModel populated only with pictures
     */
    ProductDomainModel getPictures(ProductDomainModel productDomainModel);

    /**
     * Downloads a main picture for the given product
     *
     * @param productDomainModel product what pictures we are looking for
     * @return main picture
     */
    byte[] getMainPicture(ProductDomainModel productDomainModel);
}