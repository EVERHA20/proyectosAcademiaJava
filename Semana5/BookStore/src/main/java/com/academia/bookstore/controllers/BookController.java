package com.academia.bookstore.controllers;

import com.academia.bookstore.exception.BookNotFoundException;
import com.academia.bookstore.exception.GenreNotFoundException;
import com.academia.bookstore.models.Book;
import com.academia.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id)
                    .orElseThrow(() -> new BookNotFoundException(id));
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PostMapping("/{bookId}/addGenres")
    public ResponseEntity<Book> addGenresToBook(@PathVariable Long bookId, @RequestBody List<Long> genreIds) {
        try {
            Book book = bookService.addGenresToBook(bookId, genreIds);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException | GenreNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        try {
            Book book = bookService.updateBook(id, updatedBook);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
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

    @DeleteMapping("/{bookId}/removeGenres")
    public ResponseEntity<Book> removeGenresFromBook(@PathVariable Long bookId, @RequestBody List<Long> genreIds) {
        try {
            Book book = bookService.removeGenresFromBook(bookId, genreIds);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException | GenreNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
