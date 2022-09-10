package com.hexagonal.demo.adapter.s3.product;

import com.hexagonal.demo.adapter.s3.S3Config.S3Client;
import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import com.hexagonal.demo.domain.port.spi.product.ProductS3Port;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.hexagonal.demo.adapter.s3.product.mapper.ProductS3Mapper.PRODUCT_S3_MAPPER;

@Component
@RequiredArgsConstructor
public class ProductS3Adapter implements ProductS3Port {

    public static final String PICTURE_GALLERY = "pictureGallery/";

    private final S3Client s3Client;

    @Override
    public ProductDomainModel getPictures(ProductDomainModel productDomainModel) {
        var mainPicture = getMainPicture(productDomainModel);
        var pictureGallery = s3Client.getObjects(PICTURE_GALLERY + productDomainModel.getId());

        return PRODUCT_S3_MAPPER.toDomainModel(mainPicture, pictureGallery);
    }

    @Override
    public byte[] getMainPicture(ProductDomainModel productDomainModel) {
        return s3Client.getObject(productDomainModel.getId().toString());
    }
}