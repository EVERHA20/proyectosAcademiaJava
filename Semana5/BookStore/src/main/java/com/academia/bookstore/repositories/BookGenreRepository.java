package com.academia.bookstore.repositories;

import com.academia.bookstore.models.BookGenre;
import com.academia.bookstore.models.BookGenreId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenre, BookGenreId> {
    long countByGenreId(Long genreId);

    List<BookGenre> findByGenreId(Long genreId);
}