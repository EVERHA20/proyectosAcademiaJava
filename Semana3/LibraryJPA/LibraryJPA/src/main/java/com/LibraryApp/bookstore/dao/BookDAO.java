package com.LibraryApp.bookstore.dao;

import java.util.List;

import com.LibraryApp.bookstore.entity.Book;

public interface BookDAO {
    void save(Book theBook);

    Book findById(Long theId);

    List<Book> findAll();

    List<Book> findByTitle(String theTitle);

    void update(Book theBook);

    void delete(Long id);

    int deleteAll();
}
