package com.academia.BooksDataJPA.dao;

import com.academia.BooksDataJPA.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAll();

    List<Book> findByTitle(String theTitle);

    Book findById(Long theId);

    List<Book> findByAuthor(String theAuthor);

    List<Book> findByGenre(String theGenre);

    List<Book> findByPages(Integer thePages);
}
