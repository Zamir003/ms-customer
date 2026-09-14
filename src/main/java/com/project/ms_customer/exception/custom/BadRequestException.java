package com.project.ms_customer.exception.custom;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) { super(message); }
}