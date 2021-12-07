package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.ReceiptLineDAO;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.entity.Receipt;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ReceiptLineDAOImpl implements ReceiptLineDAO {
    private static final Logger logger = Logger.getLogger(ReceiptLineDAOImpl.class);
    @Override
    public void setProductsForReceipt(Receipt receipt, Map<Integer, Product> map) {

    }

    @Override
    public List<Product> getReceiptProductsWithAmount(Receipt receipt) {
        return null;
    }

    @Override
    public void insertReceiptWithLine(long receiptId, Map<Product, Integer> mapProductsWithCount) {
        String INSERT_ORDER_IN_LIST = "INSERT INTO receipt_line(amount, product_id, receipt_id) VALUES (?,?,?)";
        Connection con;
        PreparedStatement pstm;
        try {
            con = DBManager.getConnection();
            pstm = con.prepareStatement(INSERT_ORDER_IN_LIST);

            for (Map.Entry<Product, Integer> entry : mapProductsWithCount.entrySet()) {
                pstm.setLong(1, entry.getValue());
                pstm.setLong(2, entry.getKey().getId());
                pstm.setLong(3, receiptId);
                pstm.executeUpdate();
            }
         //   con.commit();
        } catch (SQLException e) {
            logger.error("Can't insert order!",e);

        }
    }
}
