package com.epam.onlinestore.service;

import com.epam.onlinestore.dao.impl.ReceiptDAOImpl;
import com.epam.onlinestore.dao.impl.ReceiptLineDAOImpl;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.entity.Receipt;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ReceiptService {

    private static final Logger logger = Logger.getLogger(ReceiptService.class);

    private ReceiptDAOImpl receiptDAO;
    private ReceiptLineDAOImpl receiptLineDAO;

    {
        receiptDAO = new ReceiptDAOImpl();
        receiptLineDAO = new ReceiptLineDAOImpl();
    }

    public Receipt addReceipt(Receipt receipt, Map<Product, Integer> mapCart, String status) throws DaoException, SQLException {

        logger.error("Products map: " + mapCart);

        long receiptId = receiptDAO.addReceipt(receipt, status);

        receiptLineDAO.insertReceiptWithLine(receiptId, mapCart);
        logger.error("Order is: " + receipt);
        receipt.setId(receiptId);
        return receipt;
    }

    public List<Receipt> getReceiptsByUser(long userId) throws DaoException {
        return receiptDAO.findByUser(userId);
    }

    public List<Product> getReceiptProductsWithAmount(long receiptId) {
        return receiptLineDAO.getReceiptProductsWithAmount(receiptId);
    }

    public void removeReceiptById(long orderId) throws SQLException {
        receiptDAO.removeReceiptById(orderId);
    }

    public void changeStatusReceipt(long orderId, long statusId) throws SQLException {
        receiptDAO.changeStatusReceipt(orderId, statusId);
    }

    public List<Receipt> getAllReceipts() {
        return receiptDAO.getAllReceipts();
    }
}
