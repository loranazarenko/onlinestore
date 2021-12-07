package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.ProductLanguageDAO;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

public class ProductLanguageDAOImpl implements ProductLanguageDAO {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    @Override
    public void setTranslationForProduct(Product category, Language language, String description) {

    }

    @Override
    public String getTranslationForProduct(Product category, Language language) {
        return null;
    }
}
