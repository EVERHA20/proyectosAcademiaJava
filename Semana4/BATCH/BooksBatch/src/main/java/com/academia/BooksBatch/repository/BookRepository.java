package com.academia.BooksBatch.repository;

import com.academia.BooksBatch.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer>{

}
