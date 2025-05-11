package com.login.login.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException{
    private final List<String> errors;

    public BadRequestException(String message, List<String> errors) {
        super(message);
        this.errors = List.copyOf(errors); // Defensive copy
    }
}
