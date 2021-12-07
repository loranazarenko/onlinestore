package com.epam.onlinestore.service;

import com.epam.onlinestore.dao.AccountDAO;
import com.epam.onlinestore.dao.AccountDetailDAO;
import com.epam.onlinestore.dao.impl.AccountDAOImpl;
import com.epam.onlinestore.dao.impl.AccountDetailDAOImpl;
import com.epam.onlinestore.dao.impl.RoleDAOImpl;
import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.entity.AccountDetail;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogupService {

    private static final Logger log = LogManager.getLogger(LogupService.class);
    private final AccountDAO userDAO = new AccountDAOImpl();
    private final AccountDetailDAO userInfoDAO = new AccountDetailDAOImpl();

    public Account insertUserToBase(String login, String password, String name, String email) throws DaoException, ConnectionException {
        AccountDetail userInfo = new AccountDetail();
        Account userFromBase = userDAO.getByLogin(login);
        if (userFromBase != null && userFromBase.getLogin().equals(login)) {
            return null;
        }
        int roleId = 0;
        RoleDAOImpl roleDAO = new RoleDAOImpl();
        if (login.equals("user")) {
            roleId = roleDAO.findIdByName("user");
        } else if (login.equals("admin")) {
            roleId = roleDAO.findIdByName("admin");
        }
        //set login and password to a User object
        Account user = new Account();
        user.setPassword(password);
        user.setLogin(escapeForInjection(login));
        //insert the User object to DB
        log.error("User: " + user);

        userDAO.createAccount(user.getLogin(), user.getPassword(), roleId);
        log.debug("User added to DB");
        //set name and surname to a UserInfo object
        userInfo.setName(escapeForInjection(name));
        userInfo.setEmail(escapeForInjection(email));
        log.debug("User info added to DB: " + userInfo);
        //take from DB user id
        user = userDAO.getByLogin(user.getLogin());
        int userId = (int) user.getId();
        log.trace("take user id from DB: " + userId);
        userInfo.setAccountId(userId);
        //insert the UserInfo object to DB
        log.trace("new user info: " + userInfo);
        userInfoDAO.insertAccountDetail(userInfo);
        user.setAccountDetailId(userInfo.getId());
        userInfoDAO.updateAccountDetail(userInfo);
        return user;
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
