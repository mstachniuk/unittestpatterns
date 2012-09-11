package com.blogspot.mstachniuk.unittestpatterns.service.stubandspy;

import com.blogspot.mstachniuk.unittestpatterns.StrengthPasswordValidator;
import com.blogspot.mstachniuk.unittestpatterns.UserFactory;
import com.blogspot.mstachniuk.unittestpatterns.dao.UserDao;
import com.blogspot.mstachniuk.unittestpatterns.domain.User;
import com.blogspot.mstachniuk.unittestpatterns.service.AccountService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    private User lastSavedUser = null;

    @Test
    public void shouldCreateAccount() {
        // given
        AccountService accountService = new AccountService();
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

    @Test
    public void shouldCreateAccountWithoutMockitoStub() {
        // given
        AccountService accountService = new AccountService();
        User user = new User(LOGIN, PASSWORD);

        StrengthPasswordValidator validator = mock(StrengthPasswordValidator.class); // dummy ? stub
        UserDao userDao = new UserDao() {
            public void save(User user) {
            }

            public User findUserByLogin(String login) {
                return null;
            }
        };
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

    @Test
    public void shouldCreateAccountAndSaveIt() {
        // given
        AccountService accountService = new AccountService();
        User user = new User(LOGIN, PASSWORD);

        StrengthPasswordValidator validator = mock(StrengthPasswordValidator.class); // dummy ? stub
        UserDao userDao = mock(UserDao.class); // stub & spy
        UserFactory userFactory = mock(UserFactory.class); // stub
        when(userFactory.createUser(LOGIN, PASSWORD)).thenReturn(user);

        accountService.setPasswordValidator(validator);
        accountService.setUserDao(userDao);
        accountService.setUserFactory(userFactory);

        // when
        User returnedUser = accountService.createAccount(LOGIN, PASSWORD);

        // then
        verify(userDao).save(user); // spy
        assertEquals(user.getLogin(), returnedUser.getLogin());
        assertEquals(user.getPassword(), returnedUser.getPassword());
    }

    @Test
    public void shouldCreateAccountAndSaveIt2() {
        // given
        AccountService accountService = new AccountService();
        User user = new User(LOGIN, PASSWORD);

        StrengthPasswordValidator validator = mock(StrengthPasswordValidator.class); // dummy ? stub
        UserDao userDao = mock(UserDao.class); // stub & spy
        UserFactory userFactory = mock(UserFactory.class); // stub
        when(userFactory.createUser(LOGIN, PASSWORD)).thenReturn(user);

        accountService.setPasswordValidator(validator);
        accountService.setUserDao(userDao);
        accountService.setUserFactory(userFactory);

        // when
        accountService.createAccount(LOGIN, PASSWORD);

        // then
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userDao).save(captor.capture()); // spy
        assertEquals(user, captor.getValue());
    }

    @Test
    public void shouldCreateAccountAndSaveIt2WithoutMockitoSpy() {
        // given
        AccountService accountService = new AccountService();
        User user = new User(LOGIN, PASSWORD);

        StrengthPasswordValidator validator = mock(StrengthPasswordValidator.class); // dummy ? stub
        UserDao userDao = new UserDao() {
            public void save(User user) {
                lastSavedUser = user;
            }

            public User findUserByLogin(String login) {
                return null;
            }
        };
        UserFactory userFactory = mock(UserFactory.class); // stub
        when(userFactory.createUser(LOGIN, PASSWORD)).thenReturn(user);

        accountService.setPasswordValidator(validator);
        accountService.setUserDao(userDao);
        accountService.setUserFactory(userFactory);

        // when
        accountService.createAccount(LOGIN, PASSWORD);

        // then
        assertEquals(user, lastSavedUser); // spy
    }


    @Test
    public void shouldCreateAccountAndSaveIt3() {
        // given
        AccountService accountService = new AccountService();
        User user = new User(LOGIN, PASSWORD);

        StrengthPasswordValidator validator = mock(StrengthPasswordValidator.class); // dummy ? stub
        UserDao userDao = mock(UserDao.class); // stub & spy
        UserFactory userFactory = mock(UserFactory.class); // stub & spy
        when(userFactory.createUser(LOGIN, PASSWORD)).thenReturn(user);

        accountService.setPasswordValidator(validator);
        accountService.setUserDao(userDao);
        accountService.setUserFactory(userFactory);

        // when
        accountService.createAccount(LOGIN, PASSWORD);

        // then
        verify(userFactory).createUser(LOGIN, PASSWORD); // spy
        verify(userDao).save(user); // spy
    }
}
