package com.academia.BooksJPA.service;

import com.academia.BooksJPA.dao.BookDAO;
import com.academia.BooksJPA.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public List<Book> findByTitle(String theTitle) {
        return bookDAO.findByTitle(theTitle);
    }

    @Override
    public Book findById(Integer theId) {
        Book theBook = bookDAO.findById(theId);
        if (theBook == null) {
            throw new RuntimeException("Did not find book id - " + theId);
        }
        return theBook;
    }

    @Override
    public List<Book> findByAuthor(String theAuthor) {
        return bookDAO.findByAuthor(theAuthor);
    }

    @Override
    public List<Book> findByGenre(String theGenre) {
        return bookDAO.findByGenre(theGenre);
    }

    @Override
    public List<Book> findByPages(Integer thePages) {
        return bookDAO.findByPages(thePages);
    }

    @Transactional
    @Override
    public Book save(Book theBook) {
        return bookDAO.save(theBook);
    }

    @Transactional
    @Override
    public void deleteById(Integer theId) {
        bookDAO.deleteById(theId);
    }
}