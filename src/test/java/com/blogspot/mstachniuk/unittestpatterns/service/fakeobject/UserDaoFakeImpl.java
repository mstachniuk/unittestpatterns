package com.blogspot.mstachniuk.unittestpatterns.service.fakeobject;

import com.blogspot.mstachniuk.unittestpatterns.dao.UserDao;
import com.blogspot.mstachniuk.unittestpatterns.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserDaoFakeImpl implements UserDao {

    private Map<String, User> users = new HashMap<String, User>();

    public UserDaoFakeImpl() {
        users.put("login1", new User("login1", "password1"));
        users.put("login2", new User("login2", "password2"));
        users.put("login3", new User("login3", "password3"));
        users.put("login4", new User("login4", "password4"));
        users.put("login5", new User("login5", "password5"));
        users.put("login6", new User("login6", "password6"));
        users.put("login7", new User("login7", "password7"));
        users.put("login8", new User("login8", "password8"));
        users.put("login9", new User("login9", "password9"));
    }

    public void save(User user) {
        users.put(user.getLogin(), user);
    }

    public User findUserByLogin(String login) {
        return users.get(login);
    }
}
