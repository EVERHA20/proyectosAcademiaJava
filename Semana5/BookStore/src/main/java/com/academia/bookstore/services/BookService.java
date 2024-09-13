package com.academia.bookstore.services;


import com.academia.bookstore.exception.BookNotFoundException;
import com.academia.bookstore.exception.GenreNotFoundException;
import com.academia.bookstore.models.Book;
import com.academia.bookstore.models.Genre;
import com.academia.bookstore.repositories.BookGenreRepository;
import com.academia.bookstore.repositories.BookRepository;
import com.academia.bookstore.repositories.GenreRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private BookGenreRepository bookGenreRepository;
    
    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException(id)));
    }
    
    public List<Book> getBooksByIds(List<Long> bookIds) {
        return bookRepository.findAllById(bookIds);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Book saveBook(Book book) {
        if (book.getGenres() != null) {
            for (Genre genre : book.getGenres()) {
                genreRepository.findById(genre.getId())
                        .orElseThrow(() -> new GenreNotFoundException(genre.getId()));
            }
        }
        return bookRepository.save(book);
    }
    
    @Transactional
    public Book addGenresToBook(Long bookId, List<Long> genreIds) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        for (Long genreId : genreIds) {
            Genre genre = genreRepository.findById(genreId)
                    .orElseThrow(() -> new GenreNotFoundException(genreId));

            book.getGenres().add(genre);
        }

        return bookRepository.save(book);
    }
    
    @Transactional
    public Book removeGenresFromBook(Long bookId, List<Long> genreIds) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        for (Long genreId : genreIds) {
            Genre genre = genreRepository.findById(genreId)
                    .orElseThrow(() -> new GenreNotFoundException(genreId));

            book.getGenres().remove(genre);
        }

        return bookRepository.save(book);
    }

    public List<Book> getBooksByGenre(Long genreId) {
        return bookRepository.findByGenresId(genreId);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Transactional
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(updatedBook.getTitle());
                    existingBook.setAuthor(updatedBook.getAuthor());
                    existingBook.setPrice(updatedBook.getPrice());
                    existingBook.setPages(updatedBook.getPages());

                    existingBook.getGenres().clear();
                    for (Genre genre : updatedBook.getGenres()) {
                        genreRepository.findById(genre.getId())
                            .ifPresent(existingBook.getGenres()::add);
                    }

                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public double calculateTotalPrice(List<Book> books) {
        return books.stream()
        		.mapToDouble(Book::getPrice)
        		.sum();
    }

    public double calculateTotalPriceWithVAT(List<Book> books, double vatRate) {
        double totalPrice = calculateTotalPrice(books);
        return totalPrice * (1 + vatRate / 100);
    }
    
    public List<Book> getBooksMoreThanPrice(double price) {
        return bookRepository.findByPriceGreaterThan(price);
    }

    public List<Book> getBooksLessThanPrice(double price) {
        return bookRepository.findByPriceLessThan(price);
    }
}