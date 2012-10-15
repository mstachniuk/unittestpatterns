package com.blogspot.mstachniuk.unittestpatterns.service.mockinvocations;

import com.blogspot.mstachniuk.unittestpatterns.StrengthPasswordValidator;
import com.blogspot.mstachniuk.unittestpatterns.UserFactory;
import com.blogspot.mstachniuk.unittestpatterns.dao.UserDao;
import com.blogspot.mstachniuk.unittestpatterns.domain.User;
import com.blogspot.mstachniuk.unittestpatterns.service.AccountService;
import org.junit.Test;
import org.mockito.internal.debugging.VerboseMockInvocationLogger;
import org.mockito.listeners.InvocationListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AccountServiceTestWithMockInvocations {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Test
    public void shouldCreateAccount() {
        // given
        InvocationListener logger = new VerboseMockInvocationLogger();
        AccountService accountService = new AccountService();
        StrengthPasswordValidator validator = mock(StrengthPasswordValidator.class, withSettings().invocationListeners(logger));
        UserDao userDao = mock(UserDao.class, withSettings().invocationListeners(logger));
        UserFactory userFactory = mock(UserFactory.class, withSettings().invocationListeners(logger));

        when(userFactory.createUser(LOGIN, PASSWORD)).thenReturn(new User(LOGIN, PASSWORD));

        accountService.setPasswordValidator(validator);
        accountService.setUserDao(userDao);
        accountService.setUserFactory(userFactory);

        // when
        User user = accountService.createAccount(LOGIN, PASSWORD);

        // then
        assertEquals(LOGIN, user.getLogin());
        assertEquals(PASSWORD, user.getPassword());
    }
}
