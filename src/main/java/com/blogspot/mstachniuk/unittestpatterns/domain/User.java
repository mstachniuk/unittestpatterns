package com.blogspot.mstachniuk.unittestpatterns.domain;

public class User {
    private final String login;
    private final String password;

    public User(String login, String password) {
        verifyNotEmpty(login);
        verifyNotEmpty(password);
        this.login = login;
        this.password = password;
    }

    private void verifyNotEmpty(String text) {
        if (text == null) {
            throw new NullPointerException("It's null");
        }
        if (text.isEmpty()) {
            throw new IllegalArgumentException("It is empty");
        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
