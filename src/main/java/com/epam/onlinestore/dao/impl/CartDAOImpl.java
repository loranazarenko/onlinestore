package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.CartDAO;
import com.epam.onlinestore.dao.EntityMapper;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartDAOImpl implements CartDAO {

    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    private static final String ADD_TO_CART = "INSERT INTO cart " +
            " (account_id, product_id, count) VALUES (?, ?, ?)";

    private static final String GET_COUNT_CART = "SELECT count FROM cart WHERE account_id=? and product_id=?";
    private static final String GET_COUNT_CART_ALL = "SELECT count(*) as total FROM cart WHERE account_id=?";

    private static final String FIND_BY_USER_ID = "SELECT cart.count as amount, product.id as pr_id, product.name as pr_name, product.description as pr_des, product.price as pr_pr, " +
            "product.create_date as pr_create, product.color as pr_color, product.picture as pr_picture, product.count as pr_count, " +
            "product.category_id as pr_cat, product.is_deleted as isdel " +
            "FROM cart INNER JOIN product ON cart.product_id = product.id " +
            "INNER JOIN account ON cart.account_id = account.id WHERE account_id = ?;";

    private static final String UPDATE_BY_USER_ID = "UPDATE cart SET count=? WHERE account_id=? and product_id=?;";

    private static final String REMOVE_PRODUCT_FROM_BASKET = "DELETE FROM cart where account_id = ? AND product_id = ? LIMIT 1;";

    @Override
    public void add(long account_id, long product_id, int count) throws DaoException, SQLException {
        Connection connection = DBManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_TO_CART)) {
            statement.setLong(1, account_id);
            statement.setLong(2, product_id);
            statement.setInt(3, count);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to execute insert query", e);
        }
    }

    @Override
    public int countInCart(long account_id, long product_id) throws SQLException {
        Connection connection = DBManager.getConnection();
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_CART);
            preparedStatement.setLong(1, account_id);
            preparedStatement.setLong(2, product_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (Exception ex) {
            logger.error("Unable to find all products!", ex);
        }
        return count;
    }

    @Override
    public Map<Product, Integer> findUserCartProductsByUserId(long account_id) {
        Map<Product, Integer> userBasketProductList = new LinkedHashMap<>();
        try (Connection connection = DBManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID)) {
            statement.setLong(1, account_id);
            ResultSet resultSet = statement.executeQuery();
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
                Product product = mapper.mapRow(resultSet);
                if(userBasketProductList.containsKey(product)) {
                    userBasketProductList.put(product,userBasketProductList.get(product)+resultSet.getInt("amount"));
                } else{
                    userBasketProductList.put(product, resultSet.getInt("amount"));
                }
            }
            return userBasketProductList;
        } catch (SQLException e) {
            logger.error("Error while finding baskets", e);
        }
        return userBasketProductList;
    }

    @Override
    public void updateUserCartProductsByUserId(int count, long account_id, long product_id) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BY_USER_ID)) {
            statement.setLong(1, count);
            statement.setLong(2, account_id);
            statement.setLong(3, product_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while finding baskets", e);
        }
    }

    @Override
    public void removeUserBasketProduct(long account_id, long productId) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_PRODUCT_FROM_BASKET)) {
            statement.setLong(1, account_id);
            statement.setLong(2, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while removing basket: " + productId, e);
        }
    }

    public boolean remove(Product product, Account account) throws DaoException {
        boolean result = false;
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_PRODUCT_FROM_BASKET)) {
            statement.setLong(1, account.getId());
            statement.setLong(2, product.getId());
            result = statement.executeUpdate() > 0;
            return result;
        } catch (SQLException e) {
            logger.error("Error while removing basket: " + product, e);
        }
        return result;
    }

    public int countInCartAll(long userId) throws SQLException {
        Connection connection = DBManager.getConnection();
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_CART_ALL);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } catch (Exception ex) {
            logger.error("Unable to find all products!", ex);
        }
        return count;
    }

    /**
     * Extracts a product from the result set row.
     */
    private static class ProductMapper implements EntityMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs) {
            try {
                Product product = new Product();
                product.setId(rs.getLong("pr_id"));
                product.setName(rs.getString("pr_name"));
                product.setDescription(rs.getString("pr_des"));
                product.setPrice(rs.getDouble("pr_pr"));
                product.setCreate_date(rs.getDate("pr_create"));
                product.setColor(rs.getString("pr_color"));
                product.setPicture(rs.getString("pr_picture"));
                product.setCount(rs.getInt("pr_count"));
                product.setCategoryId(rs.getInt("pr_cat"));
                product.setIs_deleted(rs.getBoolean("isdel"));
                return product;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
