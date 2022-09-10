package com.hexagonal.demo.domain.model.product;

public class DiscountDomainModelBuilder {

    private final DiscountDomainModel discount;

    public DiscountDomainModelBuilder() {
        discount = new DiscountDomainModel();
    }

    public DiscountDomainModelBuilder defaultDiscount() {
        discount.setProductId(1);
        discount.setPriceAfterDiscount(75);
        discount.setDiscountInPercent(25);

        return this;
    }

    public DiscountDomainModel build() {
        return discount;
    }
}