package com.academia.bookstore.controllers;

import com.academia.bookstore.controllers.StoreController;
import com.academia.bookstore.dto.StoreResponse;
import com.academia.bookstore.models.Book;
import com.academia.bookstore.models.Genre;
import com.academia.bookstore.services.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetAllBooks() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book1");
        book.setAuthor("Author1");
        book.setPrice(25.00);
        book.setPages(300);

        List<Book> books = Arrays.asList(book);
        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/store")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book1"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetBooksByTitle() throws Exception {
        String title = "Book1";
        Book book = new Book();
        book.setId(1L);
        book.setTitle(title);
        book.setAuthor("Author1");
        book.setPrice(25.00);
        book.setPages(300);

        List<Book> books = Arrays.asList(book);
        when(bookService.getBooksByTitle(title)).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/store/search")
                .param("title", title)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(title));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetBooksByGenre() throws Exception {
        Long genreId = 1L;
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book1");
        book.setAuthor("Author1");
        book.setPrice(25.00);
        book.setPages(300);

        List<Book> books = Arrays.asList(book);
        when(bookService.getBooksByGenre(genreId)).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/store/bygenre/{genreId}", genreId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book1"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetBooksByPriceMoreThan() throws Exception {
        double price = 19.99;
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book1");
        book.setAuthor("Author1");
        book.setPrice(25.00);
        book.setPages(300);

        List<Book> books = Arrays.asList(book);
        when(bookService.getBooksMoreThanPrice(price)).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/store/books/by-price")
                .param("price", String.valueOf(price))
                .param("moreThan", "true")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book1"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetBooksByPriceLessThan() throws Exception {
        double price = 19.99;
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book1");
        book.setAuthor("Author1");
        book.setPrice(15.00);
        book.setPages(300);

        List<Book> books = Arrays.asList(book);
        when(bookService.getBooksLessThanPrice(price)).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/store/books/by-price")
                .param("price", String.valueOf(price))
                .param("moreThan", "false")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book1"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testProcessBooks() throws Exception {
        List<Long> bookIds = Arrays.asList(1L, 2L, 3L);

        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Dune");
        book1.setAuthor("Frank Herbert");
        book1.setPrice(9.99);
        book1.setPages(412);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("The Hobbit");
        book2.setAuthor("J.R.R. Tolkien");
        book2.setPrice(12.99);
        book2.setPages(310);

        Book book3 = new Book();
        book3.setId(3L);
        book3.setTitle("The Da Vinci Code");
        book3.setAuthor("Dan Brown");
        book3.setPrice(14.99);
        book3.setPages(489);

        List<Book> books = Arrays.asList(book1, book2, book3);
        double totalPrice = 37.97;
        double totalPriceWithVAT = 45.9437;

        when(bookService.getBooksByIds(bookIds)).thenReturn(books);
        when(bookService.calculateTotalPrice(books)).thenReturn(totalPrice);
        when(bookService.calculateTotalPriceWithVAT(books, 21.0)).thenReturn(totalPriceWithVAT);

        mockMvc.perform(post("/api/store/buybooks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[1, 2, 3]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(totalPrice))
                .andExpect(jsonPath("$.totalPriceWithVAT").value(totalPriceWithVAT))
                .andExpect(jsonPath("$.books[0].title").value("Dune"))
                .andExpect(jsonPath("$.books[1].title").value("The Hobbit"))
                .andExpect(jsonPath("$.books[2].title").value("The Da Vinci Code"));
    }
}