package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.EntityMapper;
import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.connection.DAOFactory;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.dao.connection.DbConst;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * a Layer class that contains methods for working with a database with a Product
 */

public class ProductDAOImpl {
    private static final Logger logger = Logger.getLogger(ProductDAOImpl.class);


    private static ProductDAOImpl productDAO;
    Connection connection;
    DAOFactory daoFactory = DAOFactory.getInstance("javabase.jdbc");

    {
        try {
            connection = daoFactory.getConnection();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductDAOImpl(DAOFactory daoFactory) throws IOException, ConnectionException {
        this.daoFactory = daoFactory;
    }

    public ProductDAOImpl() throws IOException, ConnectionException {
    }

    public static ProductDAOImpl getInstance() throws IOException, ConnectionException {
        if (productDAO == null) {
            productDAO = new ProductDAOImpl();
        }
        return productDAO;
    }

    public List<Product> findByCategory(long categoryId) throws DaoException {
        List<Product> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DbConst.FIND_PRODUCTS_BY_CATEGORY_ID_QUERY, (int) categoryId);
             ResultSet resultSet = statement.executeQuery()) {
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
                Product product = mapper.mapRow(resultSet);
                entities.add(product);
            }
        } catch (SQLException e) {
            logger.error("Unable to execute query", e);
            throw new DaoException(e.getMessage(), e);
        }
        return entities;
    }

    public Optional<Product> findByName(String name) throws DaoException {
        List<Product> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DbConst.FIND_PRODUCT_BY_NAME_QUERY, Statement.RETURN_GENERATED_KEYS);
             //      statement.setString(1, name);
             ResultSet resultSet = statement.executeQuery()) {
            ProductMapper mapper = new ProductMapper();
            if (resultSet.next()) {
                Product product = mapper.mapRow(resultSet);
                entities.add(product);
            }
        } catch (SQLException e) {
            logger.error("Unable to execute query", e);
            throw new DaoException(e.getMessage(), e);
        }
        return Optional.of(entities.get(0));
    }

    public List<Product> getProductsById(List<Long> list) throws DaoException {
        List<Product> entities = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(DbConst.FIND_PRODUCTS_BY_ID_QUERY);
            for (long id : list) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                ProductMapper mapper = new ProductMapper();
                while (resultSet.next()) {
                    entities.add(mapper.mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Unable to execute query", e);
            throw new DaoException(e.getMessage(), e);
        }
        return entities;

    }

    public void updateById(long id, Object... params) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbConst.UPDATE_PRODUCT_QUERY)) {
            //  st.setString(1, product.getName());
            //   st.setInt(2, (int) product.getId());
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i, params[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to execute query", e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    public Product save(Product product) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DbConst.SAVE_PRODUCT_QUERY
                , Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setDate(4, product.getCreate_date());
            statement.setString(5, product.getColor());
            statement.setString(6, product.getPicture());
            statement.setInt(7, product.getCount());
            statement.setLong(8, product.getCategoryId());
            statement.setInt(9, (product.getIs_deleted() ? 1 : 0));
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                long id = generatedKey.getLong(1);
                product.setId(id);
            }
        } catch (SQLException e) {
            logger.error("Unable to execute insert query", e);
        }
        return product;

    }

    public void updateProduct(Product product) {

        try (PreparedStatement st = connection.prepareStatement(DbConst.UPDATE_PRODUCT_QUERY)) {
            st.setString(1, product.getName());
            st.setString(2, product.getDescription());
            st.setDouble(3, product.getPrice());
            st.setDate(4, product.getCreate_date());
            st.setString(5, product.getColor());
            st.setString(6, product.getPicture());
            st.setInt(7, product.getCount());
            st.setLong(8, product.getCategoryId());
            st.setInt(9, (product.getIs_deleted() ? 1 : 0));
            st.setLong(10, product.getId());
            st.executeUpdate();
            // connection.commit();
        } catch (SQLException e) {
            logger.error("Unable to execute query", e);
        }
    }

    public List<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DbConst.SQL_LIST_PRODUCTS);
            preparedStatement.setInt(1, 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
                products.add(mapper.mapRow(resultSet));
            }
        } catch (Exception ex) {
            logger.error("Unable to find all products!", ex);
        }
        return products;
    }

    public List<Product> findAllLangSortBy(Language language, int limit, int offset, String sort) {
        String LIST_PRODUCT_BY_LANG_SORT = "SELECT product.id,product.name, product.price, product.create_date, product.color, product.picture, product.count," +
                " product.category_id, product.is_deleted, product_language.description FROM product" +
                " JOIN product_language ON product_language.product_id = product.id" +
                " WHERE product_language.language_id=? AND product.is_deleted=? ORDER BY " + sort + " LIMIT " + limit + "," + offset;       ///?, ?

        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(LIST_PRODUCT_BY_LANG_SORT);
            preparedStatement.setLong(1, language.getId());
            preparedStatement.setInt(2, 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            ProductMapper mapper = new ProductMapper();
            while (resultSet.next()) {
                products.add(mapper.mapRow(resultSet));
            }
        } catch (Exception ex) {
            logger.error("Unable to find all products!", ex);
        }
        return products;
    }

    public int getNumberOfRows() {
        int numOfRows = 0;
        try {
            String COUNT_PRODUCTS = "SELECT COUNT(id) as count FROM product";
            PreparedStatement preparedStatement = connection.prepareStatement(COUNT_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery(COUNT_PRODUCTS);
            while (resultSet.next()) {
                numOfRows = resultSet.getInt("count");
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return numOfRows;
    }

    public int getRestCountProduct(long item_id) {
        int rest = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(DbConst.FIND_COUNT_BY_ID);
            statement.setLong(1, item_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rest = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            logger.error("Unable to execute query", e);
        }
        return rest;
    }

    public void deleteProduct(Product product) {
        try {
            String REMOVE_PRODUCT = "DELETE FROM product where id = ?;";
            Connection connection = DBManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(REMOVE_PRODUCT);
            statement.setLong(1, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while removing product: " + product, e);
        }
    }

    /**
     * Extracts a product from the result set row.
     */
    private static class ProductMapper implements EntityMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs) {
            try {
                Product product = new Product();
                product.setId(rs.getLong(Fields.PRODUCT__ID));
                product.setName(rs.getString(Fields.PRODUCT__NAME));
                product.setDescription(rs.getString(Fields.PRODUCT__DESCRIPTION));
                product.setPrice(rs.getDouble(Fields.PRODUCT__PRICE));
                product.setCreate_date(rs.getDate(Fields.PRODUCT__CREATE_DATE));
                product.setColor(rs.getString(Fields.PRODUCT__COLOR));
                product.setPicture(rs.getString(Fields.PRODUCT__PICTURE));
                product.setCount(rs.getInt(Fields.PRODUCT__COUNT));
                product.setCategoryId(rs.getInt(Fields.PRODUCT__CATEGORY_ID));
                product.setIs_deleted(rs.getBoolean(Fields.PRODUCT__IS_DELETED));
                return product;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }


}
