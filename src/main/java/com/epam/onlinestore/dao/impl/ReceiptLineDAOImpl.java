package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.EntityMapper;
import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.ReceiptLineDAO;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.entity.Receipt;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReceiptLineDAOImpl implements ReceiptLineDAO {
    private static final Logger logger = Logger.getLogger(ReceiptLineDAOImpl.class);

    @Override
    public void setProductsForReceipt(Receipt receipt, Map<Integer, Product> map) {

    }

    @Override
    public List<Product> getReceiptProductsWithAmount(long receiptId) {
        String SELECT_PRODUCT_FROM_ORDER = "SELECT *, receipt_line.amount from product " +
                "join receipt_line on receipt_line.product_id=product.id  where receipt_id=?;";
        Connection connection;
        ArrayList<Product> products = new ArrayList<>();
        try {
            connection = DBManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_FROM_ORDER);
            preparedStatement.setLong(1, receiptId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
                products.add(mapper.mapRow(resultSet));
            }
        } catch (Exception ex) {
            logger.error("Unable to find all products!", ex);
        }
        return products;
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
        } catch (SQLException e) {
            logger.error("Can't insert order!", e);

        }
    }

    /**
     * Extracts a product from the result set row.
     */
    private static class ProductMapper implements EntityMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs) {
            try {
                Product product = new Product();
                product.setId(rs.getLong(Fields.PRODUCT__ID));
                product.setName(rs.getString(Fields.PRODUCT__NAME));
                product.setDescription(rs.getString(Fields.PRODUCT__DESCRIPTION));
                product.setPrice(rs.getDouble(Fields.PRODUCT__PRICE));
                product.setCreate_date(rs.getDate(Fields.PRODUCT__CREATE_DATE));
                product.setColor(rs.getString(Fields.PRODUCT__COLOR));
                product.setPicture(rs.getString(Fields.PRODUCT__PICTURE));
                product.setCount(rs.getInt(Fields.PRODUCT__COUNT));
                product.setCategoryId(rs.getInt(Fields.PRODUCT__CATEGORY_ID));
                product.setIs_deleted(rs.getBoolean(Fields.PRODUCT__IS_DELETED));
                return product;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
