package com.example.exception;

public class ExchangeRateFetchException extends RuntimeException {
    public ExchangeRateFetchException(String message) {
        super(message);
    }
}
