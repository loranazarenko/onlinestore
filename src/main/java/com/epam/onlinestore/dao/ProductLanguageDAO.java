package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.entity.Product;

import java.sql.SQLException;

public interface ProductLanguageDAO {

    public void setTranslationForProduct(Language language, Product product, String description) throws SQLException;

    public String getTranslationForProduct(Product product, Language language) throws SQLException;

    void updateTranslationForProduct(Language lang, Product product, String s);
}
