package com.blogspot.mstachniuk.unittestpatterns;

import com.blogspot.mstachniuk.unittestpatterns.exceptions.NotStrengthPasswordException;

public class StrengthPasswordValidator {

    public void validate(String password) {
        throw new NotStrengthPasswordException("Not Strength Password");
    }
}
