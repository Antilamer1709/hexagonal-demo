package com.hexagonal.demo.domain.model.product;

public class DiscountDomainModelTestBuilder {

    private final DiscountDomainModel discount;

    public DiscountDomainModelTestBuilder() {
        discount = new DiscountDomainModel();
    }

    public DiscountDomainModelTestBuilder defaultDiscount() {
        discount.setPriceAfterDiscount(85);
        discount.setDiscountInPercent(15);

        return this;
    }

    public DiscountDomainModelTestBuilder withDiscountInPercent(Integer discountInPercent) {
        discount.setDiscountInPercent(discountInPercent);

        return this;
    }

    public DiscountDomainModel build() {
        return discount;
    }
}