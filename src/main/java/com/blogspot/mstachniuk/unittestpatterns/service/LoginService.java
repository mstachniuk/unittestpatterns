package com.blogspot.mstachniuk.unittestpatterns.service;

import com.blogspot.mstachniuk.unittestpatterns.dao.LoginStatisticsDao;
import com.blogspot.mstachniuk.unittestpatterns.dao.UserDao;
import com.blogspot.mstachniuk.unittestpatterns.domain.User;
import com.blogspot.mstachniuk.unittestpatterns.exceptions.InvalidUserException;

public class LoginService {

    private final UserDao userDao;
    private final LoginStatisticsDao loginStatisticsDao;

    public LoginService(UserDao userDao, LoginStatisticsDao statistics) {
        this.userDao = userDao;
        this.loginStatisticsDao = statistics;
    }

    public User login(String login, String password) {
        User user = userDao.findUserByLogin(login);
        if (user == null) {
            throw new InvalidUserException("Not found user with this login");
        }
        if (!user.getPassword().equals(password)) {
            throw new InvalidUserException("Wrong password!");
        }

        loginStatisticsDao.saveStatisticsFor(user);
        return user;
    }
}
