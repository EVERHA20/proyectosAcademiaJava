package com.academia.bookstore.dto;

import com.academia.bookstore.models.Book;
import java.util.List;

public class StoreResponse {
    private List<Book> books;
    private double totalPrice;
    private double totalPriceWithVAT;


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
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
}