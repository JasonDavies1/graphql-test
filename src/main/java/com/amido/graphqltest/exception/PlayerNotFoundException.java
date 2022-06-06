package com.amido.graphqltest.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(final String message){
        super(message);
    }

}
