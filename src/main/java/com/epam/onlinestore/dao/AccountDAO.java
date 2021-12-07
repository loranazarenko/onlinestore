package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AccountDAO {

    Account createAccount(String login, String password, long role_id) throws DaoException, SQLException;

    boolean block(long account_id) throws DaoException, SQLException;

    boolean unblock(long account_id) throws DaoException, SQLException;

    List<Account> getAll() throws DaoException, SQLException;

    Account getByLogin(String login) throws DaoException;

    Optional<Account> getById(int id) throws DaoException;

    Optional<Account> updateAccount(Account account) throws DaoException;

    Account updateAccountDetailId(Account user);
}
