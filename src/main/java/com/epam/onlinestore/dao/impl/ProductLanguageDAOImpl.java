package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.ProductLanguageDAO;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.dao.connection.DbConst;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.entity.Product;
import org.apache.log4j.Logger;

import java.sql.*;

public class ProductLanguageDAOImpl implements ProductLanguageDAO {
    private static final Logger logger = Logger.getLogger(ProductLanguageDAOImpl.class);

    @Override
    public void setTranslationForProduct(Language language, Product product, String description) throws SQLException {
        Connection connection = DBManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DbConst.INSERT_PRODUCT_LANG_DESCRIPTION,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, description);
            statement.setLong(2, product.getId());
            statement.setLong(3, language.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to execute insert query", e);
        }
    }

    @Override
    public String getTranslationForProduct(Product product, Language language) throws SQLException {
        String description ="";
        Connection connection = DBManager.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(DbConst.GET_DESCRIPTION_BY_ID);
            statement.setLong(1, product.getId());
            statement.setLong(2, language.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                description = resultSet.getString("description");
            }
        } catch (SQLException e) {
            logger.error("Unable to execute query", e);
        }
        return description;
    }

    @Override
    public void updateTranslationForProduct(Language lang, Product product, String str) {
       Connection con;
        PreparedStatement pstm;
        try {
            con = DBManager.getConnection();
            pstm = con.prepareStatement(DbConst.UPDATE_PRODUCT_LANG_DESCRIPTION);
            pstm.setString(1, str.trim());
            pstm.setLong(2, product.getId());
            pstm.setLong(3, lang.getId());
            pstm.executeUpdate();
            logger.trace("Update done");
        } catch (SQLException e) {
            logger.error(e);
        }
    }

}