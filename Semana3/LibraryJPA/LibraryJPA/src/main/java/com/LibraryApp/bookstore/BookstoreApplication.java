package com.LibraryApp.bookstore;

import com.LibraryApp.bookstore.dao.BookDAO;
import com.LibraryApp.bookstore.entity.Book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(BookDAO bookDAO) {

        return runner -> {
            createBook(bookDAO);

            createMultipleBooks(bookDAO);

            readBook(bookDAO);

            queryForBooks(bookDAO);

            queryForBooksByTitle(bookDAO);

            updateBook(bookDAO);

            deleteBook(bookDAO);

            deleteAllBooks(bookDAO);
        };
    }

    private void deleteAllBooks(BookDAO bookDAO) {
        System.out.println("Deleting all books");
        int numRowsDeleted = bookDAO.deleteAll();
        System.out.println("Deleted row count: " + numRowsDeleted);
    }

    private void deleteBook(BookDAO bookDAO) {
        Long bookId = (long) 5;
        System.out.println("Deleting book id: " + bookId);
        bookDAO.delete(bookId);
    }

    private void updateBook(BookDAO bookDAO) {
        Long bookId = (long) 8;
        System.out.println("Getting book with id: " + bookId);
        Book myBook = bookDAO.findById(bookId);

        System.out.println("Updating book ...");
        myBook.setTitle("Updated Title");

        bookDAO.update(myBook);

        System.out.println("Updated book: " + myBook);
    }

    private void queryForBooksByTitle(BookDAO bookDAO) {
        List<Book> theBooks = bookDAO.findByTitle("Some Title");

        for (Book tempBook : theBooks) {
            System.out.println(tempBook);
        }
    }

    private void queryForBooks(BookDAO bookDAO) {
        List<Book> theBooks = bookDAO.findAll();

        for (Book tempBook : theBooks) {
            System.out.println(tempBook);
        }
    }

    private void readBook(BookDAO bookDAO) {
        System.out.println("Creating new book object ...");
        Book tempBook = new Book("Title", "Author", "Terror", 156);

        System.out.println("Saving the book ...");
        bookDAO.save(tempBook);

        Long theId = tempBook.getId();
        System.out.println("Saved book. Generated id: " + theId);

        System.out.println("Retrieving book with id: " + theId);
        Book myBook = bookDAO.findById(theId);

        System.out.println("Found the book: " + myBook);
    }

    private void createMultipleBooks(BookDAO bookDAO) {
        System.out.println("Creating 3 book objects ...");
        Book tempBook1 = new Book("Title1", "Author1", "Terror", 156);
        Book tempBook2 = new Book("Title2", "Author2", "Terror", 156);
        Book tempBook3 = new Book("Title3", "Author3", "Terror", 156);

        System.out.println("Saving the books ...");
        bookDAO.save(tempBook1);
        bookDAO.save(tempBook2);
        bookDAO.save(tempBook3);
    }

    private void createBook(BookDAO bookDAO) {
        System.out.println("Creating new book object ...");
        Book tempBook = new Book("Titlenew", "Author", "Terror", 156);

        System.out.println("Saving the book ...");
        bookDAO.save(tempBook);

        System.out.println("Saved book. Generated id: " + tempBook.getId());
    }
}






