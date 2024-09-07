package com.academia.BooksJPA.service;

import java.util.List;
import com.academia.BooksJPA.entity.Book;

public interface BookService {

    List<Book> findAll();

    List<Book> findByTitle(String theTitle);

    Book findById(Integer theId);

    Book save(Book theBook);

    void deleteById(Integer theId);

    List<Book> findByAuthor(String theAuthor);

    List<Book> findByGenre(String theGenre);

    List<Book> findByPages(Integer thePages);
}
