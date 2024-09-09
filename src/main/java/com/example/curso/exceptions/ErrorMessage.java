package com.example.curso.exceptions;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ErrorMessage {

    private String exception;
    private String message;
    private String path;


    public ErrorMessage(Exception exception, String path) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
    }
}
