package com.academia.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.bookstore.models.Genre;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}