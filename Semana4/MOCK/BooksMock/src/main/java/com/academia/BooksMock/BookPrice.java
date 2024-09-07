package com.academia.BooksMock;

import java.math.BigDecimal;

public class BookPrice {
	
    private final Discount discount;

    public BookPrice(Discount discount) {
        this.discount = discount;
    }

    public BigDecimal calculatePrice(BigDecimal basePrice, BigDecimal discountRate) {
        if (basePrice == null || discountRate == null) {
            throw new IllegalArgumentException("Base price and discount rate must not be null");
        }
        
        BigDecimal discountAmount = discount.calculateDiscount(basePrice, discountRate);
        
        return basePrice.subtract(discountAmount);
    }
}
