package com.LibraryApp.bookstore.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.LibraryApp.bookstore.entity.Book;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private EntityManager entityManager;

    @Autowired
    public BookDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Book theBook) {
        System.out.println("Save Book");
        entityManager.persist(theBook);
    }

    @Override
    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> theQuery = entityManager.createQuery("FROM Book", Book.class);

        return theQuery.getResultList();
    }

    @Override
    public List<Book> findByTitle(String theTitle) {
        TypedQuery<Book> theQuery = entityManager.createQuery(
                "FROM Book WHERE title = :theTitle", Book.class);

        theQuery.setParameter("theTitle", theTitle);

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Book theBook) {
        entityManager.merge(theBook);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Book theBook = entityManager.find(Book.class, id);

        if (theBook != null) {
            entityManager.remove(theBook);
        }
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Book").executeUpdate();
        return numRowsDeleted;
    }
}






