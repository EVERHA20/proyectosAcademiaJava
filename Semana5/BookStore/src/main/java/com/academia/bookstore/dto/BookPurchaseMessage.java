package com.academia.bookstore.dto;

import java.io.Serializable;
import java.util.List;

public class BookPurchaseMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Long> bookIds;
    private double totalPrice;
    private double totalPriceWithVAT;

    // Constructors, getters, and setters
    public BookPurchaseMessage() { }

    public BookPurchaseMessage(List<Long> bookIds, double totalPrice, double totalPriceWithVAT) {
        this.bookIds = bookIds;
        this.totalPrice = totalPrice;
        this.totalPriceWithVAT = totalPriceWithVAT;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPriceWithVAT() {
        return totalPriceWithVAT;
    }

    public void setTotalPriceWithVAT(double totalPriceWithVAT) {
        this.totalPriceWithVAT = totalPriceWithVAT;
    }

    @Override
    public String toString() {
        return "BookPurchaseMessage{" +
                "bookIds=" + bookIds +
                ", totalPrice=" + totalPrice +
                ", totalPriceWithVAT=" + totalPriceWithVAT +
                '}';
    }
}