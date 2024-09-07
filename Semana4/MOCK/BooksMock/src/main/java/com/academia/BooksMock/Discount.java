package com.academia.BooksMock;

import java.math.BigDecimal;

public class Discount {
	
    public BigDecimal calculateDiscount(BigDecimal basePrice, BigDecimal discountRate) {
        return basePrice.multiply(discountRate);
    }
}
