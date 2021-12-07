package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Category;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {

    List<Category> getAllCategories() throws DaoException, SQLException;

    Category getCategoryById(int id) throws SQLException;

    long save(Category category) throws DaoException;
 //   long update(Category category) throws DaoException;
    List<Category> findByName(String name) throws DaoException, SQLException;
    List<Category> getAllCategoriesWithLang(Language language) throws SQLException;

}
