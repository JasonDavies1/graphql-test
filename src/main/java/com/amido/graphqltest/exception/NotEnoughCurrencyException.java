package com.amido.graphqltest.exception;

public class NotEnoughCurrencyException extends RuntimeException {
    public NotEnoughCurrencyException(final String message) {
        super(message);
    }
}
