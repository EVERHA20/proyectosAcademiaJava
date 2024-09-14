package com.academia.bookstore.controllers;

import com.academia.bookstore.exception.GenreNotFoundException;
import com.academia.bookstore.models.Genre;
import com.academia.bookstore.services.GenreService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GenreControllerTest {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
    }

    @Test
    public void getAllGenres_ReturnsGenres() throws Exception {
        Genre genre1 = new Genre(1L, "Fiction", Collections.emptySet());
        Genre genre2 = new Genre(2L, "Science", Collections.emptySet());

        when(genreService.getAllGenres()).thenReturn(Arrays.asList(genre1, genre2));

        mockMvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Fiction"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Science"));
    }

    @Test
    public void getGenreById_ReturnsGenre() throws Exception {
        Genre genre = new Genre(1L, "Fiction", Collections.emptySet());

        when(genreService.getGenreById(anyLong())).thenReturn(genre);

        mockMvc.perform(get("/api/genres/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Fiction"));
    }

    @Test
    public void getGenreById_ReturnsNotFound() throws Exception {
        when(genreService.getGenreById(anyLong())).thenThrow(new GenreNotFoundException(1L));

        mockMvc.perform(get("/api/genres/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createGenre_ReturnsCreatedGenre() throws Exception {
        Genre genre = new Genre(1L, "Fiction", Collections.emptySet());
        when(genreService.saveGenre(any(Genre.class))).thenReturn(genre);

        mockMvc.perform(post("/api/genres")
                        .contentType("application/json")
                        .content("{\"name\": \"Fiction\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Fiction"));
    }

    @Test
    public void updateGenre_ReturnsUpdatedGenre() throws Exception {
        Genre updatedGenre = new Genre(1L, "Science Fiction", Collections.emptySet());
        when(genreService.updateGenre(anyLong(), any(Genre.class))).thenReturn(updatedGenre);

        mockMvc.perform(put("/api/genres/1")
                        .contentType("application/json")
                        .content("{\"name\": \"Science Fiction\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Science Fiction"));
    }

    @Test
    public void updateGenre_ReturnsNotFound() throws Exception {
        when(genreService.updateGenre(anyLong(), any(Genre.class))).thenThrow(new GenreNotFoundException(1L));

        mockMvc.perform(put("/api/genres/1")
                        .contentType("application/json")
                        .content("{\"name\": \"Science Fiction\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteGenre_ReturnsNoContent() throws Exception {
        doNothing().when(genreService).deleteGenre(anyLong());

        mockMvc.perform(delete("/api/genres/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteGenre_ReturnsNotFound() throws Exception {
        doThrow(new GenreNotFoundException(1L)).when(genreService).deleteGenre(anyLong());

        mockMvc.perform(delete("/api/genres/1"))
                .andExpect(status().isNotFound());
    }
}