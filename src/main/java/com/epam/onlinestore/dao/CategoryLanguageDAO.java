package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Category;
import com.epam.onlinestore.entity.Language;

public interface CategoryLanguageDAO {

    public void setTranslationForCategory(Category category, Language language, String name);

    public String getTranslationForCategory(Category category, Language language);

}
