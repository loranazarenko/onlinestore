package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.CategoryLanguageDAO;
import com.epam.onlinestore.entity.Category;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

public class CategoryLanguageImpl implements CategoryLanguageDAO {
    private static final Logger log = Logger.getLogger(CategoryLanguageImpl.class);
    @Override
    public void setTranslationForCategory(Category category, Language language, String name) {

    }

    @Override
    public String getTranslationForCategory(Category category, Language language) {
        return null;
    }
}
