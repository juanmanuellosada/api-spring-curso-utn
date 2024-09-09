package com.example.curso.exceptions.custom;

import java.io.Serial;

public class ConflictException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final String DESCRIPTION = "Conflict Exception (409)";

    public ConflictException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}