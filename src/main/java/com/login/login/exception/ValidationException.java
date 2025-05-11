package com.login.login.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidationException extends RuntimeException {
    private final Map<String, String> errors;

    public ValidationException(BindingResult bindingResult){
        this.errors = new HashMap<>();
        for(FieldError fieldError : bindingResult.getFieldErrors()){
            this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    public ValidationException(String message){
        super(message);
        this.errors = new HashMap<>();
        this.errors.put("message", message);
    }

}
