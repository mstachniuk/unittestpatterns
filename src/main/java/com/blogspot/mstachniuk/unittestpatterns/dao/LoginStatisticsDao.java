package com.blogspot.mstachniuk.unittestpatterns.dao;

import com.blogspot.mstachniuk.unittestpatterns.domain.User;

public interface LoginStatisticsDao {
    public void saveStatisticsFor(User user);
}
