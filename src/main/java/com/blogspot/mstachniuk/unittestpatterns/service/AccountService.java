package com.blogspot.mstachniuk.unittestpatterns.service;

import com.blogspot.mstachniuk.unittestpatterns.StrengthPasswordValidator;
import com.blogspot.mstachniuk.unittestpatterns.UserFactory;
import com.blogspot.mstachniuk.unittestpatterns.dao.UserDao;
import com.blogspot.mstachniuk.unittestpatterns.domain.User;
import com.blogspot.mstachniuk.unittestpatterns.exceptions.InvalidUserException;

public class AccountService {

    private StrengthPasswordValidator passwordValidator;

    private UserDao userDao;

    private UserFactory userFactory;

    public User createAccount(String login, String password) {
        validateLogin(login);
        passwordValidator.validate(password);
        checkExistAccount(login);

        // indirect input
        User user = userFactory.createUser(login, password);
        userDao.save(user);
        return user;
    }

    private void validateLogin(String login) {
        if (login == null)
            throw new NullPointerException("Login should not be null");
        if (login.length() > 6) {
            throw new InvalidUserException("This user not exist");
        }
    }

    private void checkExistAccount(String login) {
        if (existAccount(login)) {
            throw new InvalidUserException("User with login: " + login + " exist in database.");
        }
    }

    boolean existAccount(String login) {
        return userDao.findUserByLogin(login) != null;
    }

    public void setPasswordValidator(StrengthPasswordValidator passwordValidator) {
        this.passwordValidator = passwordValidator;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserFactory(UserFactory userFactory) {
        this.userFactory = userFactory;
    }
}
