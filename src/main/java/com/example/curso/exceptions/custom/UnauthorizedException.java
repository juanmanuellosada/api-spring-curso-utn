package com.example.curso.exceptions.custom;

import java.io.Serial;

public class UnauthorizedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final String DESCRIPTION = "Unauthorized Exception (401)";

    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}