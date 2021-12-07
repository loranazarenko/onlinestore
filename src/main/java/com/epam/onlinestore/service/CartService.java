package com.epam.onlinestore.service;

import com.epam.onlinestore.dao.impl.CartDAOImpl;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Map;

public class CartService {
    private static final Logger log = Logger.getLogger(CartService.class);
    private CartDAOImpl cartDAO;

    {
        cartDAO = new CartDAOImpl();
    }

    public void addItemToCart(long account_id, long item_id, int count) throws DaoException, SQLException {
        cartDAO.add(account_id, item_id, count);
    }
    public int countInCart(long account_id, long product_id) throws SQLException {
        return cartDAO.countInCart(account_id, product_id);
    }

    public Map<Product, Integer> findUserCartProductsByUserId(long account_id) {
        return cartDAO.findUserCartProductsByUserId(account_id);
    }

    public double calculateTotalPrice(Map<Product, Integer> userBasketProductList) {
        double totalPrice = 0;
        for (Product userBasketProduct : userBasketProductList.keySet()) {
            totalPrice += userBasketProduct.getPrice()*userBasketProductList.get(userBasketProduct);
        }
        double scale = Math.pow(10, 2);
        return Math.ceil(totalPrice * scale) / scale;
    }

    public void removeUserBasketProduct(long account_id, long productId) {
        cartDAO.removeUserBasketProduct(account_id, productId);
    }

    public void updateUserBasketProduct(int count, long account_id, long productId) {
        if(count == 0){
            cartDAO.removeUserBasketProduct(account_id, productId);
        } else {
            cartDAO.updateUserCartProductsByUserId(count, account_id, productId);
        }
    }

    public int countInCartAll(long userId) throws SQLException {
        return cartDAO.countInCartAll(userId);
    }
}
