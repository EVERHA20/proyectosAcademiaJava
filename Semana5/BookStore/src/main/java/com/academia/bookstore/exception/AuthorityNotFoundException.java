package com.academia.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorityNotFoundException extends RuntimeException {
    public AuthorityNotFoundException(Long authorityId) {
        super("Authority not found with id: " + authorityId);
    }
}
