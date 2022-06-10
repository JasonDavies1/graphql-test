package com.amido.graphqltest.exception;

public class ListingNotFoundException extends RuntimeException {
    public ListingNotFoundException(final String message) {
        super(message);
    }
}
