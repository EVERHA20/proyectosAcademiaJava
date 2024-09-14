package com.academia.bookstore.controllers;

import com.academia.bookstore.dto.StoreResponse;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StoreControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private StoreController storeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(storeController).build();
    }

    @Test
    public void getAllBooks_ReturnsBooks() throws Exception {
        Book book1 = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());
        Book book2 = new Book(2L, "Title2", "Author2", 29.99, 250, Collections.emptySet());

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/store"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Title2"));
    }

    @Test
    public void getBooksByTitle_ReturnsBooks() throws Exception {
        Book book1 = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());
        Book book2 = new Book(2L, "Title1", "Author2", 29.99, 250, Collections.emptySet());

        when(bookService.getBooksByTitle(anyString())).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/store/search?title=Title1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[1].title").value("Title1"));
    }

    @Test
    public void getBooksByGenre_ReturnsBooks() throws Exception {
        Book book1 = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());
        Book book2 = new Book(2L, "Title2", "Author2", 29.99, 250, Collections.emptySet());

        when(bookService.getBooksByGenre(anyLong())).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/store/bygenre/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void getBooksByPrice_ReturnsBooksMoreThanPrice() throws Exception {
        Book book1 = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());
        Book book2 = new Book(2L, "Title2", "Author2", 29.99, 250, Collections.emptySet());

        when(bookService.getBooksMoreThanPrice(anyDouble())).thenReturn(Arrays.asList(book2));

        mockMvc.perform(get("/api/store/books/by-price")
                        .param("price", "20")
                        .param("moreThan", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2));
    }

    @Test
    public void getBooksByPrice_ReturnsBooksLessThanPrice() throws Exception {
        Book book1 = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());

        when(bookService.getBooksLessThanPrice(anyDouble())).thenReturn(Arrays.asList(book1));

        mockMvc.perform(get("/api/store/books/by-price")
                        .param("price", "20")
                        .param("moreThan", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void processBooks_ReturnsStoreResponse() throws Exception {
        Book book1 = new Book(1L, "Title1", "Author1", 19.99, 300, Collections.emptySet());
        Book book2 = new Book(2L, "Title2", "Author2", 29.99, 250, Collections.emptySet());

        when(bookService.getBooksByIds(anyList())).thenReturn(Arrays.asList(book1, book2));
        when(bookService.calculateTotalPrice(anyList())).thenReturn(49.98);
        when(bookService.calculateTotalPriceWithVAT(anyList(), anyDouble())).thenReturn(60.18);

        StoreResponse storeResponse = new StoreResponse();
        storeResponse.setBooks(Arrays.asList(book1, book2));
        storeResponse.setTotalPrice(49.98);
        storeResponse.setTotalPriceWithVAT(60.18);

        mockMvc.perform(post("/api/store/buybooks")
                        .contentType("application/json")
                        .content("[1, 2]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(49.98))
                .andExpect(jsonPath("$.totalPriceWithVAT").value(60.18));
    }

    @Test
    public void processBooks_ReturnsNotFound() throws Exception {
        when(bookService.getBooksByIds(anyList())).thenReturn(Collections.emptyList());

        mockMvc.perform(post("/api/store/buybooks")
                        .contentType("application/json")
                        .content("[1, 2]"))
                .andExpect(status().isNotFound());
    }
}