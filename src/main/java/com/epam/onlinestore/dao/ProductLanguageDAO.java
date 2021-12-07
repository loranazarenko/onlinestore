package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.entity.Product;

public interface ProductLanguageDAO {

    public void setTranslationForProduct(Product category, Language language, String description);

    public String getTranslationForProduct(Product category, Language language);
}
