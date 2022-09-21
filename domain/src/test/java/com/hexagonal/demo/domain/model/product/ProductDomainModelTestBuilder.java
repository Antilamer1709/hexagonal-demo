package com.hexagonal.demo.domain.model.product;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.stream.IntStream.range;

public class ProductDomainModelTestBuilder {

    public static final String MAIN_PICTURE = "Main picture";
    public static final String FIRST_GALLERY_PICTURE = "First gallery picture";
    public static final String SECOND_GALLERY_PICTURE = "Second gallery picture";

    public static final int TEST_ID = 1;
    public static final int TEST_ORIGINAL_PRICE = 100;
    public static final int TEST_AVAILABLE_IN_WAREHOUSE = 6;
    public static final String TEST_PRODUCT_NAME = "Test product name";
    public static final String TEST_PRODUCT_DESCRIPTION = "Test product description";

    private final ProductDomainModel product;

    public ProductDomainModelTestBuilder() {
        product = new ProductDomainModel();
    }

    public ProductDomainModelTestBuilder defaultProduct() {
        jpaProduct();
        warehouseProduct();
        s3Product();

        return this;
    }

    public ProductDomainModelTestBuilder jpaProduct() {
        product.setId(TEST_ID);
        product.setName(TEST_PRODUCT_NAME);
        product.setOriginalPrice(TEST_ORIGINAL_PRICE);
        product.setDescription(TEST_PRODUCT_DESCRIPTION);

        return this;
    }

    public ProductDomainModelTestBuilder warehouseProduct() {
        product.setId(TEST_ID);
        product.setAvailableInWarehouse(TEST_AVAILABLE_IN_WAREHOUSE);

        return this;
    }

    public ProductDomainModelTestBuilder s3Product() {
        product.setMainPicture(MAIN_PICTURE.getBytes());
        product.setPictureGallery(List.of(
                FIRST_GALLERY_PICTURE.getBytes(),
                SECOND_GALLERY_PICTURE.getBytes()
        ));

        return this;
    }

    public ProductDomainModelTestBuilder withDiscount(DiscountDomainModel discountDomainModel) {
        product.applyDiscount(discountDomainModel);

        return this;
    }

    public ProductDomainModelTestBuilder withId(Integer id) {
        product.setId(id);

        return this;
    }

    public ProductDomainModelTestBuilder withPictureGallery(List<byte[]> pictureGallery) {
        product.setPictureGallery(pictureGallery);

        return this;
    }

    public ProductDomainModelTestBuilder withOriginalPrice(Integer originalPrice) {
        product.setOriginalPrice(originalPrice);

        return this;
    }

    public ProductDomainModelTestBuilder withAvailableInWarehouse(Integer availableInWarehouse) {
        product.setAvailableInWarehouse(availableInWarehouse);

        return this;
    }

    public ProductDomainModel build() {
        return product;
    }

    public List<ProductDomainModel> buildMany(Integer amount) {
        List<ProductDomainModel> result = new ArrayList<>();
        result.add(product); // The first element is the one that has been built

        // Next elements (come from second element) are copy of the original with index added to the fields
        range(2, amount + 1).forEach(index -> {
            var newProduct = new ProductDomainModel();

            if (nonNull(product.getId()))
                newProduct.setId(product.getId() + index - 1);
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