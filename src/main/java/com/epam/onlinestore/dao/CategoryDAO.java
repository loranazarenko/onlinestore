package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Category;
import com.epam.onlinestore.exception.DaoException;

import java.util.List;

public interface CategoryDAO {
    List<Category> getCategories() throws DaoException;

    Category getById(int categoryId) throws DaoException;

}
