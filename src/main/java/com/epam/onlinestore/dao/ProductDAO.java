package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {


    /**
     * Returns a list of all products from the database ordered by product ID. The list is never null and
     * is empty when the database does not contain any product.
     *
     * @return A list of all products from the database ordered by product ID.
     * @throws DaoException If something fails at database level.
     */
    List<Product> findAll(int offset, int limit) throws DaoException;

    /**
     * Method to find product by category in database
     *
     * @param categoryId category ID
     * @return List of products
     * @throws DaoException If something fails at database level.
     */
    List<Product> findByCategory(long categoryId) throws DaoException;

    /**
     * Method to get product by name from database
     *
     * @param name name of product
     * @return Optional of product
     * @throws DaoException If something fails at database level.
     */
    Optional<Product> findByName(String name) throws DaoException;

    /**
     * Method to update product by ID in database
     *
     * @param id      ID of product to update
     * @param product new product information
     * @throws DaoException If something fails at database level.
     */
    void updateById(long id, Product product) throws DaoException;

    /**
     * Method to save product into table
     *
     * @param item product object to save
     * @throws DaoException If something fails at database level.
     */
    Product save(Product item) throws DaoException;

    /**
     * Method to get product by name from database
     *
     * @param listId name of product
     * @return Optional of product
     * @throws DaoException If something fails at database level.
     */
    List<Product> getProductsById(List<Long> listId);

    List<Product> getAllProductsWithLang(Language language);

}
