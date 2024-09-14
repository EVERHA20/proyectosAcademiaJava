package com.academia.bookstore.controllers;

import com.academia.bookstore.exception.BookNotFoundException;
import com.academia.bookstore.exception.GenreNotFoundException;
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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetAllBooks() throws Exception {
        Set<Genre> genres = new HashSet<>(); // Initialize genres if necessary
        List<Book> books = Arrays.asList(new Book(1L, "Book1", "Author1", 25.00, 300, genres));
        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book1"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetBookById() throws Exception {
        Long bookId = 1L;
        Set<Genre> genres = new HashSet<>();
        Book book = new Book(bookId, "Book1", "Author1", 25.00, 300, genres);
        when(bookService.getBookById(bookId)).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", bookId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book1"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetBookByIdNotFound() throws Exception {
        Long bookId = 1L;
        when(bookService.getBookById(bookId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", bookId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testCreateBook() throws Exception {
        Set<Genre> genres = new HashSet<>();
        Book book = new Book(1L, "Book1", "Author1", 25.00, 300, genres);
        when(bookService.saveBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Book1\",\"author\":\"Author1\",\"price\":25.00,\"pages\":300}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Book1"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testAddGenresToBook() throws Exception {
        Long bookId = 1L;
        List<Long> genreIds = Arrays.asList(1L, 2L);
        Set<Genre> genres = new HashSet<>();
        Book book = new Book(bookId, "Book1", "Author1", 25.00, 300, genres);
        when(bookService.addGenresToBook(anyLong(), anyList())).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books/{bookId}/addGenres", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[1, 2]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book1"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testAddGenresToBookNotFound() throws Exception {
        Long bookId = 1L;
        List<Long> genreIds = Arrays.asList(1L, 2L);
        when(bookService.addGenresToBook(anyLong(), anyList())).thenThrow(new BookNotFoundException(bookId));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books/{bookId}/addGenres", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[1, 2]"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUpdateBook() throws Exception {
        Long bookId = 1L;
        Set<Genre> genres = new HashSet<>();
        Book updatedBook = new Book(bookId, "UpdatedBook", "UpdatedAuthor", 30.00, 350, genres);
        when(bookService.updateBook(anyLong(), any(Book.class))).thenReturn(updatedBook);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/{id}", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"UpdatedBook\",\"author\":\"UpdatedAuthor\",\"price\":30.00,\"pages\":350}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("UpdatedBook"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUpdateBookNotFound() throws Exception {
        Long bookId = 1L;
        Set<Genre> genres = new HashSet<>();
        Book updatedBook = new Book(bookId, "UpdatedBook", "UpdatedAuthor", 30.00, 350, genres);
        when(bookService.updateBook(anyLong(), any(Book.class))).thenThrow(new BookNotFoundException(bookId));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/{id}", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"UpdatedBook\",\"author\":\"UpdatedAuthor\",\"price\":30.00,\"pages\":350}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testDeleteBook() throws Exception {
        Long bookId = 1L;
        doNothing().when(bookService).deleteBook(bookId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}", bookId))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testDeleteBookNotFound() throws Exception {
        Long bookId = 1L;
        doThrow(new BookNotFoundException(bookId)).when(bookService).deleteBook(bookId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}", bookId))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetBooksByTitle() throws Exception {
        String title = "Book1";
        Set<Genre> genres = new HashSet<>();
        List<Book> books = Arrays.asList(new Book(1L, title, "Author1", 25.00, 300, genres));
        when(bookService.getBooksByTitle(title)).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/search")
                .param("title", title)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(title));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetBooksByGenre() throws Exception {
        Long genreId = 1L;
        Set<Genre> genres = new HashSet<>();
        List<Book> books = Arrays.asList(new Book(1L, "Book1", "Author1", 25.00, 300, genres));
        when(bookService.getBooksByGenre(genreId)).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/bygenre/{genreId}", genreId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book1"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testRemoveGenresFromBook() throws Exception {
        Long bookId = 1L;
        List<Long> genreIds = Arrays.asList(1L, 2L);
        Set<Genre> genres = new HashSet<>();
        Book book = new Book(bookId, "Book1", "Author1", 25.00, 300, genres);
        when(bookService.removeGenresFromBook(anyLong(), anyList())).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{bookId}/removeGenres", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[1, 2]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book1"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testRemoveGenresFromBookNotFound() throws Exception {
        Long bookId = 1L;
        List<Long> genreIds = Arrays.asList(1L, 2L);
        when(bookService.removeGenresFromBook(anyLong(), anyList())).thenThrow(new BookNotFoundException(bookId));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{bookId}/removeGenres", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[1, 2]"))
                .andExpect(status().isNotFound());
    }
}
