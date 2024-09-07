package com.academia.BooksDataJPA.service;

import com.academia.BooksDataJPA.dao.BookRepository;
import com.academia.BooksDataJPA.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository) {
        this.bookRepository = theBookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByTitle(String theTitle) {
        return bookRepository.findByTitle(theTitle);
    }

    @Override
    public Book findById(Integer theId) {
        Optional<Book> result = bookRepository.findById(theId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Did not find book id - " + theId);
        }
    }

    @Override
    public Book save(Book theBook) {
        return bookRepository.save(theBook);
    }

    @Override
    public void deleteById(Integer theId) {
        bookRepository.deleteById(theId);
    }

    @Override
    public List<Book> findByAuthor(String theAuthor) {
        return bookRepository.findByAuthor(theAuthor);
    }

    @Override
    public List<Book> findByGenre(String theGenre) {
        return bookRepository.findByGenre(theGenre);
    }

    @Override
    public List<Book> findByPages(Integer thePages) {
        return bookRepository.findByPages(thePages);
    }
}
