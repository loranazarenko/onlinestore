package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;

import java.sql.SQLException;
import java.util.Map;

public interface CartDAO {

    /**
     * Method to add product into cart
     *
     * @param item_id product object to save
     * @throws DaoException If something fails at database level.
     */
    void add(long account_id, long item_id, int count) throws DaoException, SQLException;

    int countInCart(long account_id, long productId) throws SQLException;

    Map<Product, Integer> findUserCartProductsByUserId(long account_id);

    void updateUserCartProductsByUserId(int count, long account_id, long product_id);

    void removeUserBasketProduct(long account_id, long productId);
}
