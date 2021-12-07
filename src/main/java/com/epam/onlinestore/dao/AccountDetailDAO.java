package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.AccountDetail;
import com.epam.onlinestore.exception.DaoException;

import java.sql.SQLException;

public interface AccountDetailDAO {

    AccountDetail getAccountDetailById(long id) throws DaoException;

    void insertAccountDetail(AccountDetail accountDetail) throws DaoException;

    void updateAccountDetail(AccountDetail accountDetail) throws SQLException;

}
