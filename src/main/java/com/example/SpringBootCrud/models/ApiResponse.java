package com.example.SpringBootCrud.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private String message;
    private Boolean isSuccess;

    public ApiResponse(String message, boolean b) {
        this.isSuccess = b;
        this.message = message;
    }
}
