package com.academia.bookstore.services;

import com.academia.bookstore.models.Book;
import com.academia.bookstore.models.BookGenre;
import com.academia.bookstore.models.BookGenreId;
import com.academia.bookstore.models.Genre;
import com.academia.bookstore.repositories.BookGenreRepository;
import com.academia.bookstore.repositories.BookRepository;
import com.academia.bookstore.repositories.GenreRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BookGenreService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookGenreRepository bookGenreRepository;

    @Transactional
    public BookGenre addBookGenre(BookGenre bookGenre) {
        return bookGenreRepository.save(bookGenre);
    }

    @Transactional
    public void removeBookGenre(BookGenreId bookGenreId) {
        bookGenreRepository.deleteById(bookGenreId);
    }
    
    @Transactional
    public Book addGenresToBook(Long bookId, List<Long> genreIds) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        for (Long genreId : genreIds) {
            Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new RuntimeException("Genre not found"));
            BookGenreId bookGenreId = new BookGenreId(bookId, genreId);
            BookGenre bookGenre = new BookGenre(bookGenreId, book, genre);

            if (!bookGenreRepository.existsById(bookGenreId)) {
                bookGenreRepository.save(bookGenre);
            }
        }

        return book;
    }

}