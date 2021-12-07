package com.epam.onlinestore.service;

import com.epam.onlinestore.dao.AccountDAO;
import com.epam.onlinestore.dao.AccountDetailDAO;
import com.epam.onlinestore.dao.impl.AccountDAOImpl;
import com.epam.onlinestore.dao.impl.AccountDetailDAOImpl;
import com.epam.onlinestore.dao.impl.RoleDAOImpl;
import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.entity.AccountDetail;
import com.epam.onlinestore.entity.Role;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AccountService {

    private static final Logger log = LogManager.getLogger(AccountService.class);
    private final AccountDAO userDAO = new AccountDAOImpl();
    private final AccountDetailDAO userInfoDAO = new AccountDetailDAOImpl();

    public Account insertUserToBase(String login, String password, String name, String email) throws DaoException, SQLException {
        AccountDetail userInfo = new AccountDetail();
        Account userFromBase = userDAO.getByLogin(login);
        if (userFromBase != null && userFromBase.getLogin().equals(login)) {
            return null;
        }

        Account user = new Account();
        user.setPassword(password);
        user.setLogin(escapeForInjection(login));
        user.setUserStatuses(1);
        log.error("User: " + user);

        RoleDAOImpl roleDao = new RoleDAOImpl();
        Optional<Role> role = roleDao.findByName("user");
        if (!role.isPresent()) {
            return null;
        }

        user = userDAO.createAccount(user.getLogin(), user.getPassword(), role.get().getId());
        log.debug("User info added to DB: " + userInfo);

        userInfo.setName(escapeForInjection(name));
        userInfo.setEmail(escapeForInjection(email));
        userInfo.setAccountId(user.getId());

        log.trace("new account detail: " + userInfo);
        userInfoDAO.insertAccountDetail(userInfo);

        user.setAccountDetailId(userInfo.getId());
        userDAO.updateAccountDetailId(user);
        return user;
    }

    public List<Account> getListOfUsers() throws DaoException, SQLException {
        return userDAO.getAll();
    }

    public Account getByLogin(String login) throws DaoException {
        return userDAO.getByLogin(login);
    }

    public void updateAccount(Account user) throws DaoException {
        userDAO.updateAccount(user);
    }

    static String escapeForInjection(String param) {
        return param.replace("!", "!!").
                replace("<", "!<").
                replace(">", "!").
                replace("%", "!%").
                replace("_", "!_").
                replace("[", "![");
    }


}
