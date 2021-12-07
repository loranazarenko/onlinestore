package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Receipt;
import com.epam.onlinestore.exception.DaoException;

import java.util.List;

public interface ReceiptDAO {
    int create(int userId, String comment) throws DaoException;

    int getReceiptCount() throws DaoException;

    List<Receipt> getByUser(int userId) throws DaoException;

    Receipt getById(int orderId) throws DaoException;


}
