package com.project.ms_customer.exception;

import com.project.ms_customer.exception.custom.BadRequestException;
import com.project.ms_customer.exception.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public record ErrorResponse(Instant ts, int status, String error, String message) {}

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse nf(NotFoundException ex) {
        return new ErrorResponse(Instant.now(), 404, "NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse br(BadRequestException ex) {
        return new ErrorResponse(Instant.now(), 400, "BAD_REQUEST", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse other(Exception ex) {
        return new ErrorResponse(Instant.now(), 500, "INTERNAL_ERROR", ex.getMessage());
    }
}