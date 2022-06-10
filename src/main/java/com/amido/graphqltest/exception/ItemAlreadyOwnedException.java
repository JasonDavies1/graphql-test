package com.amido.graphqltest.exception;

public class ItemAlreadyOwnedException extends RuntimeException {
    public ItemAlreadyOwnedException(final String message){
        super(message);
    }
}
