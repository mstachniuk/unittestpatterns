package com.blogspot.mstachniuk.unittestpatterns.exceptions;

public class InvalidUserException extends RuntimeException {

    public InvalidUserException(String message) {
        super(message);
    }
}
