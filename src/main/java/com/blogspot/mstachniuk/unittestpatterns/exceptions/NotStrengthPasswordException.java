package com.blogspot.mstachniuk.unittestpatterns.exceptions;

public class NotStrengthPasswordException extends RuntimeException {

    public NotStrengthPasswordException(String message) {
        super(message);
    }
}
