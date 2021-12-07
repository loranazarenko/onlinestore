package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.CategoryDAO;
import com.epam.onlinestore.dao.EntityMapper;
import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.dao.connection.DbConst;
import com.epam.onlinestore.entity.Category;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * a Layer class that contains methods for working with a database with a Category
 */

public class CategoryDAOImpl implements CategoryDAO {
    private static final Logger log = Logger.getLogger(CategoryDAOImpl.class);

    @Override
    public List<Category> getAllCategories() throws SQLException {
        List <Category> list = new ArrayList<>();
        Connection con = DBManager.getConnection();
        ResultSet rs;
        try {
            PreparedStatement pstm = con.prepareStatement(DbConst.FIND_ALL_CATEGORY);
            rs = pstm.executeQuery();
            CategoryMapper mapper = new CategoryMapper();
            while (rs.next()) {
                list.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return list;
    }

    @Override
    public Category getCategoryById(int id) throws SQLException {
        Category category = new Category();
        Connection con = DBManager.getConnection();
        ResultSet rs;
        try {
            PreparedStatement pstm = con.prepareStatement(DbConst.FIND_CATEGORY_BY_ID);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            CategoryMapper mapper = new CategoryMapper();
            if (rs.next()) {
                category = mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return category;
    }

    @Override
    public long save(Category category) {
        return 0;
    }

    @Override
    public List<Category> findByName(String name) throws DaoException, SQLException {
        List <Category> list = new ArrayList<>();
        Connection con = DBManager.getConnection();
        ResultSet rs;
        try {
            PreparedStatement pstm = con.prepareStatement(DbConst.FIND_CATEGORY_BY_NAME);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            CategoryMapper mapper = new CategoryMapper();
            while (rs.next()) {
                list.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return list;
    }

    public List<Category> getAllCategoriesWithLang(Language language) throws SQLException {

        List <Category> list = new ArrayList<>();
        Connection con = DBManager.getConnection();
        ResultSet rs;
        try {
            PreparedStatement pstm = con.prepareStatement(DbConst.SQLLISTCATEGORYBYLANG);
            pstm.setLong(1, language.getId());
            rs = pstm.executeQuery();
            CategoryMapper mapper = new CategoryMapper();
            while (rs.next()) {
                list.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return list;
    }


    /**
     * Extracts a category from the result set row.
     */
    private static class CategoryMapper implements EntityMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs) {
            Category category = new Category();
            try {
                category.setId(rs.getInt(Fields.CATEGORY__ID));
                category.setCategoryName(rs.getString(Fields.CATEGORY__NAME));
            } catch (SQLException e) {
               log.error(e);
            }
            return category;
        }
    }
}
