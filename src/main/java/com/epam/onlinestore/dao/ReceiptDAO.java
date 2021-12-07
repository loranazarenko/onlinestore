package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.entity.Receipt;
import com.epam.onlinestore.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ReceiptDAO {

    List<Receipt> findByUser(long userId) throws DaoException;

    public long addReceipt(Receipt receipt, String status) throws DaoException, SQLException;

    int getReceiptCount() throws DaoException;

    List<Receipt> getByUser(int userId) throws DaoException;

    Receipt getById(int orderId) throws DaoException;


    void removeReceiptById(long orderId) throws SQLException;

    void changeStatusReceipt(long orderId, long statusId) throws SQLException;

    List<Receipt> getAllReceipts();
}
