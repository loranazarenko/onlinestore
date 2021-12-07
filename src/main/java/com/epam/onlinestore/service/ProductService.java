package com.epam.onlinestore.service;

import com.epam.onlinestore.Controller;
import com.epam.onlinestore.dao.impl.ProductDAOImpl;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductService {
    private static final Logger log = Logger.getLogger(Controller.class);
    private ProductDAOImpl productDAO;

    public ProductService(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }

   /* public int getCountOfProducts() throws DaoException {
        return productDAO.findAll();
    }*/

    public List<Product> getListOfProducts(List<Long> listId) throws DaoException {
        return productDAO.getProductsById(listId);
    }

    public List<Product> getAllProducts() throws DaoException {
        return productDAO.findAll();
    }
}
