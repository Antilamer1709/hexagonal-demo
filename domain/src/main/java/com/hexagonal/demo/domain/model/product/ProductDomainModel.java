package com.hexagonal.demo.domain.model.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.Objects.nonNull;

@Data
@NoArgsConstructor
public class ProductDomainModel {

    public static final int HUNDRED_PERCENT = 100;

    private Integer id;
    private String name;
    private Integer originalPrice;
    private Integer availableInWarehouse;
    private String description;
    private byte[] mainPicture;
    private List<byte[]> pictureGallery;
    private DiscountDomainModel discount;

    public ProductDomainModel(Integer id) {
        this.id = id;
    }

    public void applyDiscount(DiscountDomainModel discount) {
        this.discount = discount;
        if (nonNull(this.discount)) {
            this.discount.setPriceAfterDiscount(this.originalPrice - (this.originalPrice * discount.getDiscountInPercent() / HUNDRED_PERCENT));
        }
    }

    public void setAvailableInWarehouse(Integer availableInWarehouse) {
        if (availableInWarehouse < 0) {
            throw new IllegalArgumentException("The available amount cannot be less than zero!");
        }
        this.availableInWarehouse = availableInWarehouse;
    }
}