package com.academia.BooksJPA.dao;

import com.academia.BooksJPA.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private final EntityManager entityManager;

    @Autowired
    public BookDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("FROM Book", Book.class);
        return query.getResultList();
    }

    @Override
    public List<Book> findByTitle(String theTitle) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class);
        query.setParameter("title", theTitle);
        return query.getResultList();
    }

    @Override
    public Book findById(Integer theId) {
        return entityManager.find(Book.class, theId);
    }

    @Override
    public List<Book> findByAuthor(String theAuthor) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.author = :author", Book.class);
        query.setParameter("author", theAuthor);
        return query.getResultList();
    }

    @Override
    public List<Book> findByGenre(String theGenre) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.genre = :genre", Book.class);
        query.setParameter("genre", theGenre);
        return query.getResultList();
    }

    @Override
    public List<Book> findByPages(Integer thePages) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.pages = :pages", Book.class);
        query.setParameter("pages", thePages);
        return query.getResultList();
    }

    @Override
    public Book save(Book theBook) {
    	Book dbBook = entityManager.merge(theBook);
        return dbBook;
    }

    @Override
    public void deleteById(Integer theId) {
        Book theBook = entityManager.find(Book.class, theId);
        if (theBook != null) {
            entityManager.remove(theBook);
        }
    }
}