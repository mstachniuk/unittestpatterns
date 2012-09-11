package com.blogspot.mstachniuk.unittestpatterns.service;

import com.blogspot.mstachniuk.unittestpatterns.StrengthPasswordValidator;
import com.blogspot.mstachniuk.unittestpatterns.UserFactory;
import com.blogspot.mstachniuk.unittestpatterns.dao.UserDao;
import com.blogspot.mstachniuk.unittestpatterns.domain.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AccountServiceTestWithPartialMocking {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    @Test
    public void shouldCreateAccount() {
        // given
        AccountService accountService = spy(new AccountService()); // partial mocking
        //when(accountService.existAccount(LOGIN)).thenReturn(false);
        doReturn(false).when(accountService).existAccount(LOGIN);
        User user = new User(LOGIN, PASSWORD);

        StrengthPasswordValidator validator = mock(StrengthPasswordValidator.class); // dummy ? stub
        UserDao userDao = mock(UserDao.class); // stub
        UserFactory userFactory = mock(UserFactory.class); // stub
        when(userFactory.createUser(LOGIN, PASSWORD)).thenReturn(user);

        accountService.setPasswordValidator(validator);
        accountService.setUserDao(userDao);
        accountService.setUserFactory(userFactory);

        // when
        User returnedUser = accountService.createAccount(LOGIN, PASSWORD);

        // then
        assertEquals(LOGIN, returnedUser.getLogin());
        assertEquals(PASSWORD, returnedUser.getPassword());
    }
}
