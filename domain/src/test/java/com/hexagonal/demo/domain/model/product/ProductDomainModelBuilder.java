package com.hexagonal.demo.domain.model.product;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.stream.IntStream.range;

public class ProductDomainModelBuilder {

    public static final String MAIN_PICTURE = "Main picture";
    public static final String FIRST_GALLERY_PICTURE = "First gallery picture";
    public static final String SECOND_GALLERY_PICTURE = "Second gallery picture";

    private static final int TEST_ID = 1;
    private static final int TEST_ORIGINAL_PRICE = 100;
    private static final int TEST_AVAILABLE_IN_WAREHOUSE = 6;
    private static final String TEST_PRODUCT_NAME = "Test product name";
    private static final String TEST_PRODUCT_DESCRIPTION = "Test product description";

    private final ProductDomainModel product;

    public ProductDomainModelBuilder() {
        product = new ProductDomainModel();
    }

    public ProductDomainModelBuilder defaultProduct() {
        jpaProduct();
        warehouseProduct();
        s3Product();

        return this;
    }

    public ProductDomainModelBuilder jpaProduct() {
        product.setId(TEST_ID);
        product.setName(TEST_PRODUCT_NAME);
        product.setOriginalPrice(TEST_ORIGINAL_PRICE);
        product.setDescription(TEST_PRODUCT_DESCRIPTION);

        return this;
    }

    public ProductDomainModelBuilder warehouseProduct() {
        product.setId(TEST_ID);
        product.setAvailableInWarehouse(TEST_AVAILABLE_IN_WAREHOUSE);

        return this;
    }

    public ProductDomainModelBuilder s3Product() {
        product.setMainPicture(MAIN_PICTURE.getBytes());
        product.setPictureGallery(List.of(
                FIRST_GALLERY_PICTURE.getBytes(),
                SECOND_GALLERY_PICTURE.getBytes()
        ));

        return this;
    }

    public ProductDomainModelBuilder withDiscount(DiscountDomainModel discountDomainModel) {
        product.applyDiscount(discountDomainModel);

        return this;
    }

    public ProductDomainModelBuilder withPictureGallery(List<byte[]> pictureGallery) {
        product.setPictureGallery(pictureGallery);

        return this;
    }

    public ProductDomainModel build() {
        return product;
    }

    public List<ProductDomainModel> buildMany(Integer amount) {
        List<ProductDomainModel> result = new ArrayList<>();

        range(1, amount + 1).forEach(index -> {
            var newProduct = new ProductDomainModel();

            if (nonNull(product.getId()))
                newProduct.setId(index);
            if (nonNull(product.getName()))
                newProduct.setName(product.getName() + index);
            if (nonNull(product.getOriginalPrice()))
                newProduct.setOriginalPrice(product.getOriginalPrice() + index * 10);
            if (nonNull(product.getAvailableInWarehouse()))
                newProduct.setAvailableInWarehouse(5 + index);
            if (nonNull(product.getDescription()))
                newProduct.setDescription(product.getDescription() + index);
            if (nonNull(product.getMainPicture()))
                newProduct.setMainPicture((MAIN_PICTURE + index).getBytes());

            if (nonNull(product.getPictureGallery())) {
                newProduct.setPictureGallery(List.of(
                        (FIRST_GALLERY_PICTURE + index).getBytes(),
                        (SECOND_GALLERY_PICTURE + index).getBytes()
                ));
            }

            result.add(newProduct);
        });

        return result;
    }
}