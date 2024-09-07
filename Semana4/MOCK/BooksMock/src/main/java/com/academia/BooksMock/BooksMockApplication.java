package com.academia.BooksMock;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksMockApplication {

	public static void main(String[] args) {
        Discount discount = new Discount();
        BookPrice bookPrice = new BookPrice(discount);

        BigDecimal basePrice = new BigDecimal("100.00");
        BigDecimal discountRate = new BigDecimal("0.10");

        BigDecimal finalPrice = bookPrice.calculatePrice(basePrice, discountRate);

        System.out.println("Final Price: " + finalPrice);
    }
}
