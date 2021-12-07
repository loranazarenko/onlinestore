package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface AccountDAO {

    boolean createAccount(String login, String password, long role_id) throws DaoException, ConnectionException;

    List<Account> getAll() throws DaoException;

    Account getByLogin(String login) throws DaoException;

    Optional<Account> getById(int id) throws DaoException;

    Optional<Account> updateAccount(Account account) throws DaoException;

}
