package com.epam.onlinestore.service;

import com.epam.onlinestore.dao.impl.ProductDAOImpl;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;


/**
 * Class that contains methods for working with Products
 */

public class ProductService {
    private static final Logger log = Logger.getLogger(ProductService.class);
    private ProductDAOImpl productDAO;

    {
        try {
            productDAO = new ProductDAOImpl();
        } catch (IOException | ConnectionException e) {
            log.error(e);
        }
    }

    public int getRestCountProduct(long item_id){
       return productDAO.getRestCountProduct(item_id);
    }

    public List<Product> getProductById(List<Long> listId) throws DaoException {
        return productDAO.getProductsById(listId);
    }

    public List<Product> getAllProducts() throws DaoException {
        return productDAO.findAll();
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(Product product) {
        productDAO.deleteProduct(product);
    }

    public List<Product> findAllLangSortBy(Language language, int limit, int offset, String sort) {
        return productDAO.findAllLangSortBy(language, limit, offset, sort);
    }

    public Product addNewProduct(Product product) throws DaoException {
        return productDAO.save(product);
    }

    public int getNumberOfRows() {
        return productDAO.getNumberOfRows();
    }

}
