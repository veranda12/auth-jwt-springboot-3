package com.login.login.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResponse<T> {
    private String message;
    private List<String> errors;
    private T data;

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
