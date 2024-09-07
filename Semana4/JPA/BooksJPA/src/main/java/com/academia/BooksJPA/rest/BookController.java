package com.academia.BooksJPA.rest;

import com.academia.BooksJPA.entity.Book;
import com.academia.BooksJPA.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/library")
public class BookController {
	
	private BookService bookService;
	
    @Autowired
    public BookController(BookService theBookService) {
    	bookService = theBookService;
    }
    
    @GetMapping("/books")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/books/{bookId}")
    public Book getBook(@PathVariable Integer bookId) {
        Book theBook = bookService.findById(bookId);
        if (theBook == null) {
            throw new RuntimeException("Book id not found - " + bookId);
        }
        return theBook;
    }
    
    @GetMapping("/books/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookService.findByAuthor(author);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book theBook) {
        return bookService.save(theBook);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable Integer bookId, @RequestBody Book theBook) {
        theBook.setId(bookId);
        return bookService.save(theBook);
    }

    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable Integer bookId) {
        Book tempBook = bookService.findById(bookId);
        if (tempBook == null) {
            throw new RuntimeException("Book id not found - " + bookId);
        }
        bookService.deleteById(bookId);
        return "Deleted book id - " + bookId;
    }
}
