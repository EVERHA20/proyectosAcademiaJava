package com.academia.bookstore.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class BookGenreId implements Serializable {
	
    private Long bookId;
    private Long genreId;

    public BookGenreId() {}

    public BookGenreId(Long bookId, Long genreId) {
        this.bookId = bookId;
        this.genreId = genreId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookGenreId that = (BookGenreId) o;
        return Objects.equals(bookId, that.bookId) &&
               Objects.equals(genreId, that.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, genreId);
    }
}