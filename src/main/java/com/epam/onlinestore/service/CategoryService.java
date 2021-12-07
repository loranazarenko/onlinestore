package com.epam.onlinestore.service;

import com.epam.onlinestore.dao.impl.CategoryDAOImpl;
import com.epam.onlinestore.entity.Category;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.Logger;


/**
 * Class that contains methods for working with entity category
 */
import java.sql.SQLException;
import java.util.List;

public class CategoryService {
    private static final Logger log = Logger.getLogger(CategoryService.class);
    private CategoryDAOImpl categoryDAO;

    {
        categoryDAO = new CategoryDAOImpl();
    }
    public Category getCategoryById(long listId) throws SQLException {
        return categoryDAO.getCategoryById((int) listId);
    }

    public List<Category> getAllCategories() throws DaoException, SQLException {
        return categoryDAO.getAllCategories();
    }

    public List<Category> getAllCategoriesWithLang(Language language) throws SQLException {
        return categoryDAO.getAllCategoriesWithLang(language);
    }

}
