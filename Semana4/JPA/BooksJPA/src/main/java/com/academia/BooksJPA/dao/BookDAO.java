package com.academia.BooksJPA.dao;

import com.academia.BooksJPA.entity.Book;
import java.util.List;

public interface BookDAO {

    List<Book> findAll();

    List<Book> findByTitle(String theTitle);

    Book findById(Integer theId);

    List<Book> findByAuthor(String theAuthor);

    List<Book> findByGenre(String theGenre);

    List<Book> findByPages(Integer thePages);

    Book save(Book theBook);

    void deleteById(Integer theId);
}
