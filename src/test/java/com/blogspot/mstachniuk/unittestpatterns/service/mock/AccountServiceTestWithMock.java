package com.blogspot.mstachniuk.unittestpatterns.service.mock;

import com.blogspot.mstachniuk.unittestpatterns.StrengthPasswordValidator;
import com.blogspot.mstachniuk.unittestpatterns.UserFactory;
import com.blogspot.mstachniuk.unittestpatterns.dao.UserDao;
import com.blogspot.mstachniuk.unittestpatterns.domain.User;
import com.blogspot.mstachniuk.unittestpatterns.service.AccountService;
import org.easymock.EasyMock;
import org.junit.Test;

public class AccountServiceTestWithMock {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    @Test
    public void shouldCreateAccountAndSaveIt() {
        // given
        AccountService accountService = new AccountService();
        User user = new User(LOGIN, PASSWORD);

        StrengthPasswordValidator validator = EasyMock.createNiceMock(StrengthPasswordValidator.class); // mock
        UserDao userDao = EasyMock.createMock(UserDao.class); // mock
        EasyMock.expect(userDao.findUserByLogin(LOGIN)).andReturn(null);
        userDao.save(user);
        EasyMock.expectLastCall();

        UserFactory userFactory = EasyMock.createMock(UserFactory.class); // mock
        EasyMock.expect(userFactory.createUser(LOGIN, PASSWORD)).andReturn(user);

        accountService.setPasswordValidator(validator);
        accountService.setUserDao(userDao);
        accountService.setUserFactory(userFactory);

        EasyMock.replay(validator);
        EasyMock.replay(userDao);
        EasyMock.replay(userFactory);

        // when
        accountService.createAccount(LOGIN, PASSWORD);

        // then
        EasyMock.verify(validator);
        EasyMock.verify(userDao);
        EasyMock.verify(userFactory);
    }

    @Test
    public void shouldCreateAccountAndSaveItAndCheckOrder() {
        // given
        AccountService accountService = new AccountService();
        User user = new User(LOGIN, PASSWORD);

        StrengthPasswordValidator validator = EasyMock.createNiceMock(StrengthPasswordValidator.class); // dummy ? stub

        UserDao userDao = EasyMock.createStrictMock(UserDao.class); // mock
        UserFactory userFactory = EasyMock.createStrictMock(UserFactory.class); // mock

        // 1st invocation
        EasyMock.expect(userDao.findUserByLogin(LOGIN)).andReturn(null);

        // 2nd invocation
        EasyMock.expect(userFactory.createUser(LOGIN, PASSWORD)).andReturn(user);

        // 3th invocation
        userDao.save(user);
        EasyMock.expectLastCall();

        accountService.setPasswordValidator(validator);
        accountService.setUserDao(userDao);
        accountService.setUserFactory(userFactory);

        EasyMock.replay(validator);
        EasyMock.replay(userDao);
        EasyMock.replay(userFactory);

        // when
        accountService.createAccount(LOGIN, PASSWORD);

        // then
        EasyMock.verify(userDao);
        EasyMock.verify(userFactory);
    }
}
