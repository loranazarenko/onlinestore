package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.CategoryDAO;
import com.epam.onlinestore.entity.Category;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    @Override
    public List<Category> getCategories() throws DaoException {
        return null;
    }

    @Override
    public Category getById(int categoryId) throws DaoException {


        return null;
    }
}
