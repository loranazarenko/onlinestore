package com.epam.onlinestore.dao;

import com.epam.onlinestore.exception.DaoException;

import java.sql.SQLException;

public interface StatusDAO {

    long getStatusIdByName(String name) throws DaoException, SQLException;

    String getStatusNameById(long id) throws DaoException, SQLException;
}
