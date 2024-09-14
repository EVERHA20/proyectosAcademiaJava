package com.academia.bookstore.controllers;

import com.academia.bookstore.exception.BookNotFoundException;
import com.academia.bookstore.exception.GenreNotFoundException;
import com.academia.bookstore.models.Book;
import com.academia.bookstore.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void getAllBooks_ReturnsBooks() throws Exception {
        Book book1 = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());
        Book book2 = new Book(2L, "Title2", "Author2", 29.99, 250, Collections.emptySet());

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Title2"));
    }

    @Test
    public void getBookById_ReturnsBook() throws Exception {
        Book book = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());

        when(bookService.getBookById(anyLong())).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Title1"));
    }

    @Test
    public void getBookById_ReturnsNotFound() throws Exception {
        when(bookService.getBookById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createBook_ReturnsCreated() throws Exception {
        Book book = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());

        when(bookService.saveBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType("application/json")
                        .content("{\"title\": \"Title1\", \"author\": \"Author1\", \"price\": 19.99, \"pages\": 300}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Title1"));
    }

    @Test
    public void addGenresToBook_ReturnsUpdatedBook() throws Exception {
        Book book = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());

        when(bookService.addGenresToBook(anyLong(), anyList())).thenReturn(book);

        mockMvc.perform(post("/api/books/1/addGenres")
                        .contentType("application/json")
                        .content("[1, 2]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void addGenresToBook_ReturnsNotFound() throws Exception {
        when(bookService.addGenresToBook(anyLong(), anyList())).thenThrow(BookNotFoundException.class);

        mockMvc.perform(post("/api/books/1/addGenres")
                        .contentType("application/json")
                        .content("[1, 2]"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateBook_ReturnsUpdatedBook() throws Exception {
        Book updatedBook = new Book(1L, "UpdatedTitle", "UpdatedAuthor", 25.99, 350, Collections.emptySet());

        when(bookService.updateBook(anyLong(), any(Book.class))).thenReturn(updatedBook);

        mockMvc.perform(put("/api/books/1")
                        .contentType("application/json")
                        .content("{\"title\": \"UpdatedTitle\", \"author\": \"UpdatedAuthor\", \"price\": 25.99, \"pages\": 350}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("UpdatedTitle"));
    }

    @Test
    public void updateBook_ReturnsNotFound() throws Exception {
        when(bookService.updateBook(anyLong(), any(Book.class))).thenThrow(BookNotFoundException.class);

        mockMvc.perform(put("/api/books/1")
                        .contentType("application/json")
                        .content("{\"title\": \"UpdatedTitle\", \"author\": \"UpdatedAuthor\", \"price\": 25.99, \"pages\": 350}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteBook_ReturnsNoContent() throws Exception {
        doNothing().when(bookService).deleteBook(anyLong());

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteBook_ReturnsNotFound() throws Exception {
        doThrow(BookNotFoundException.class).when(bookService).deleteBook(anyLong());

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getBooksByTitle_ReturnsBooks() throws Exception {
        Book book1 = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());
        Book book2 = new Book(2L, "Title1", "Author2", 29.99, 250, Collections.emptySet());

        when(bookService.getBooksByTitle(anyString())).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/books/search?title=Title1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[1].title").value("Title1"));
    }

    @Test
    public void getBooksByGenre_ReturnsBooks() throws Exception {
        Book book1 = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());
        Book book2 = new Book(2L, "Title2", "Author2", 29.99, 250, Collections.emptySet());

        when(bookService.getBooksByGenre(anyLong())).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/books/bygenre/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void removeGenresFromBook_ReturnsUpdatedBook() throws Exception {
        Book book = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());

        when(bookService.removeGenresFromBook(anyLong(), anyList())).thenReturn(book);

        mockMvc.perform(delete("/api/books/1/removeGenres")
                        .contentType("application/json")
                        .content("[1, 2]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void removeGenresFromBook_ReturnsNotFound() throws Exception {
        when(bookService.removeGenresFromBook(anyLong(), anyList())).thenThrow(BookNotFoundException.class);

        mockMvc.perform(delete("/api/books/1/removeGenres")
                        .contentType("application/json")
                        .content("[1, 2]"))
                .andExpect(status().isNotFound());
    }
}
