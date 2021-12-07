package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.ReceiptDAO;
import com.epam.onlinestore.entity.Receipt;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.util.List;

public class ReceiptDAOImpl implements ReceiptDAO {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    @Override
    public int create(int userId, String comment) throws DaoException {
        return 0;
    }

    @Override
    public int getReceiptCount() throws DaoException {
        return 0;
    }

    @Override
    public List<Receipt> getByUser(int userId) throws DaoException {
        return null;
    }

    @Override
    public Receipt getById(int orderId) throws DaoException {
        return null;
    }
}
