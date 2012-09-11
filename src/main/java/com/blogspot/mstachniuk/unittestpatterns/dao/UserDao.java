package com.blogspot.mstachniuk.unittestpatterns.dao;

import com.blogspot.mstachniuk.unittestpatterns.domain.User;

public interface UserDao {
    public void save(User user);

    public User findUserByLogin(String login);
}
