package com.academia.BooksMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BookPriceTest {


    @Mock
    private Discount discount;

    @InjectMocks
    private BookPrice bookPrice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculatePrice() {
        BigDecimal basePrice = new BigDecimal("100.00");
        BigDecimal discountRate = new BigDecimal("0.10");
        BigDecimal discountAmount = new BigDecimal("10.00");

        when(discount.calculateDiscount(basePrice, discountRate)).thenReturn(discountAmount);
        BigDecimal finalPrice = bookPrice.calculatePrice(basePrice, discountRate);
        assertEquals(new BigDecimal("90.00"), finalPrice);
    }
    
    @Test
    public void testCalculatePrice2() {
        BigDecimal basePrice = new BigDecimal("80.00");
        BigDecimal discountRate = new BigDecimal("0.25");
        BigDecimal discountAmount = new BigDecimal("20.00");

        when(discount.calculateDiscount(basePrice, discountRate)).thenReturn(discountAmount);
        BigDecimal finalPrice = bookPrice.calculatePrice(basePrice, discountRate);
        assertEquals(new BigDecimal("60.00"), finalPrice);
    }

    @Test
    public void testCalculatePriceWithNulls() {
        try {
        	bookPrice.calculatePrice(null, new BigDecimal("0.10"));
        } catch (IllegalArgumentException e) {
            assertEquals("Base price and discount rate must not be null", e.getMessage());
        }

        try {
            bookPrice.calculatePrice(new BigDecimal("100.00"), null);
        } catch (IllegalArgumentException e) {
            assertEquals("Base price and discount rate must not be null", e.getMessage());
        }
    }
}
