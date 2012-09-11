package com.blogspot.mstachniuk.unittestpatterns.service.fakeobject;

import com.blogspot.mstachniuk.unittestpatterns.StrengthPasswordValidator;
import com.blogspot.mstachniuk.unittestpatterns.UserFactory;
import com.blogspot.mstachniuk.unittestpatterns.dao.UserDao;
import com.blogspot.mstachniuk.unittestpatterns.domain.User;
import com.blogspot.mstachniuk.unittestpatterns.service.AccountService;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTestWithFakeObject {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    @Test
    public void shouldCreateAccount() {
        // given
        AccountService accountService = new AccountService();
        User user = new User(LOGIN, PASSWORD);

        StrengthPasswordValidator validator = mock(StrengthPasswordValidator.class);
        UserDao userDao = new UserDaoFakeImpl(); // Fake Object
        UserFactory userFactory = mock(UserFactory.class);
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
