package com.academia.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.bookstore.dto.StoreResponse;
import com.academia.bookstore.models.Book;
import com.academia.bookstore.services.BookService;

@RestController
@RequestMapping("/api/store")
public class StoreController {
	
    @Autowired
    private BookService bookService;

	
	@GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
	
    @GetMapping("/search")
    public ResponseEntity<List<Book>> getBooksByTitle(@RequestParam String title) {
        List<Book> books = bookService.getBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/bygenre/{genreId}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable Long genreId) {
        List<Book> books = bookService.getBooksByGenre(genreId);
        return ResponseEntity.ok(books);
    }
    

    @GetMapping("/books/by-price")
    public ResponseEntity<List<Book>> getBooksByPrice(
            @RequestParam double price, 
            @RequestParam(defaultValue = "true") boolean moreThan) {
        
        List<Book> books;
        if (moreThan) {
            books = bookService.getBooksMoreThanPrice(price);
        } else {
            books = bookService.getBooksLessThanPrice(price);
        }
        
        return ResponseEntity.ok(books);
    }
	
    @PostMapping("/buybooks")
    public ResponseEntity<StoreResponse> processBooks(@RequestBody List<Long> bookIds) {
        List<Book> books = bookService.getBooksByIds(bookIds);
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        double totalPrice = bookService.calculateTotalPrice(books);
        double vatRate = 21.0;
        double totalPriceWithVAT = bookService.calculateTotalPriceWithVAT(books, vatRate);

        StoreResponse response = new StoreResponse();
        response.setBooks(books);
        response.setTotalPrice(totalPrice);
        response.setTotalPriceWithVAT(totalPriceWithVAT);

        return ResponseEntity.ok(response);
    }
}
