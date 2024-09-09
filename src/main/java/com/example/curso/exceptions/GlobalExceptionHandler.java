package com.example.curso.exceptions;

import com.example.curso.exceptions.custom.BadRequestException;
import com.example.curso.exceptions.custom.ForbiddenException;
import com.example.curso.exceptions.custom.ResourceNotFoundException;
import com.example.curso.exceptions.custom.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            ResourceNotFoundException.class,
            JpaObjectRetrievalFailureException.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            DuplicateKeyException.class,
            HttpRequestMethodNotSupportedException.class,
            MissingRequestHeaderException.class, //
            MissingServletRequestParameterException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            DataIntegrityViolationException.class,
            MethodArgumentTypeMismatchException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ ForbiddenException.class

    })
    @ResponseBody
    public ErrorMessage ForbiddenRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({ UnauthorizedException.class
    })
    public void unauthorized() {
        // No se envia mensaje de error
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ErrorMessage fatalErrorUnexpectedException(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }
}
