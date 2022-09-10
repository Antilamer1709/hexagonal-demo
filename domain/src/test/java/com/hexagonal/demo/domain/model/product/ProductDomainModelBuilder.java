package com.hexagonal.demo.domain.model.product;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;

public class ProductDomainModelBuilder {

    public static final String MAIN_PICTURE = "Main picture";
    public static final String FIRST_GALLERY_PICTURE = "First gallery picture";
    public static final String SECOND_GALLERY_PICTURE = "Second gallery picture";

    private final ProductDomainModel product;

    public ProductDomainModelBuilder() {
        product = new ProductDomainModel();
    }

    public ProductDomainModelBuilder defaultProduct() {
        product.setId(1);
        product.setName("Test product name");
        product.setOriginalPrice(100);
        product.setAvailableInWarehouse(5);
        product.setDescription("Test product description");
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

    public ProductDomainModel build() {
        return product;
    }

    public List<ProductDomainModel> buildMany(Integer amount) {
        List<ProductDomainModel> result = new ArrayList<>();

        range(1, amount + 1).forEach(index -> {
            var newProduct = new ProductDomainModel();

            newProduct.setId(index);
            newProduct.setName(product.getName() + index);
            newProduct.setOriginalPrice(product.getOriginalPrice() + index * 10);
            newProduct.setAvailableInWarehouse(5 + index);
            newProduct.setDescription(product.getDescription() + index);
            newProduct.setMainPicture((MAIN_PICTURE + index).getBytes());
            newProduct.setPictureGallery(List.of(
                    (FIRST_GALLERY_PICTURE + index).getBytes(),
                    (SECOND_GALLERY_PICTURE + index).getBytes()
            ));

            result.add(newProduct);
        });

        return result;
    }
}