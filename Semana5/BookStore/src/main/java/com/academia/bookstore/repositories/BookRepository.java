package com.academia.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.bookstore.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    List<Book> findAllById(Iterable<Long> ids);
    
    List<Book> findByPriceGreaterThan(double price);
    
    List<Book> findByPriceLessThan(double price);
    
    @Query("SELECT DISTINCT b FROM Book b JOIN FETCH b.genres g WHERE g.id = :genreId")
    List<Book> findByGenresId(@Param("genreId") Long genreId);
}