package com.blogspot.mstachniuk.unittestpatterns;

import com.blogspot.mstachniuk.unittestpatterns.domain.User;

public class UserFactory {

    public User createUser(String login, String password) {
        return new User(login, password);
    }
}
