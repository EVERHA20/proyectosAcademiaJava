package com.academia.BooksBatch.config;

import org.springframework.batch.item.ItemProcessor;
import com.academia.BooksBatch.entity.Book;

public class BookProcessor implements ItemProcessor<Book, Book> {

    @Override
    public Book process(Book book) throws Exception {
        if ("Science Fiction".equals(book.getGenre()) || "Fantasy".equals(book.getGenre())) {
            return book; 
        } else {
            return null;
        }
    }
}