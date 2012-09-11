package com.blogspot.mstachniuk.unittestpatterns.service.dummy;

import com.blogspot.mstachniuk.unittestpatterns.dao.LoginStatisticsDao;
import com.blogspot.mstachniuk.unittestpatterns.dao.UserDao;
import com.blogspot.mstachniuk.unittestpatterns.domain.User;
import com.blogspot.mstachniuk.unittestpatterns.service.LoginService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest {

    public static final String LOGIN = "username";
    public static final String PASSWORD = "password";

    @Test
    public void shouldLoginUser() {
        // given
        LoginStatisticsDao statistics = mock(LoginStatisticsDao.class); // dummy

        UserDao userDao = mock(UserDao.class);
        when(userDao.findUserByLogin(LOGIN)).thenReturn(new User(LOGIN, PASSWORD));

        LoginService loginService = new LoginService(userDao, statistics);

        // when
        User user = loginService.login(LOGIN, PASSWORD);

        // then
        assertEquals(LOGIN, user.getLogin());
    }

}
