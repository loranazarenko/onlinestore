package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.EntityMapper;
import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.connection.DAOFactory;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM product ORDER BY id";
    private static final String FIND_PRODUCTS_BY_CATEGORY_ID_QUERY = "SELECT * FROM product WHERE category_id=?";
    private static final String FIND_PRODUCT_BY_NAME_QUERY = "SELECT * FROM product WHERE name=?";
    private static final String SAVE_PRODUCT_QUERY = "INSERT INTO product " +
            " (category_id, name, description, price, create_date, color, size, picture, count,category_id,is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_QUERY = "UPDATE product SET category_id=?, " +
            "name=?, description=?, price=? WHERE id=?";
    private static final String FIND_PRODUCTS_BY_ID_QUERY = "SELECT * FROM product WHERE id=?";

    private static ProductDAOImpl productDAO;
    Connection connection;

    //  private DAOFactory daoFactory;
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

    public List<Product> findAll() throws DaoException {

        ArrayList<Product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_LIST_ORDER_BY_ID);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String picture = resultSet.getString("picture");
                Product product = new Product(id, name, price, picture);
                products.add(product);
            }
        } catch (Exception ex) {
            logger.error("Unable to find all products!", ex);
            throw new DaoException(ex.getMessage(), ex);
        }
        return products;
    }

    public List<Product> findByCategory(long categoryId) throws DaoException {
        List<Product> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_PRODUCTS_BY_CATEGORY_ID_QUERY, (int) categoryId);
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
        try (PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_NAME_QUERY, Statement.RETURN_GENERATED_KEYS);
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
            PreparedStatement statement = connection.prepareStatement(FIND_PRODUCTS_BY_ID_QUERY);
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_QUERY)) {
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

    public long save(Product product) throws DaoException {

        long result = 0;
        try (PreparedStatement statement = connection.prepareStatement(SAVE_PRODUCT_QUERY)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setDate(4, product.getCreate_date());
            statement.setString(5, product.getColor());
            statement.setInt(6, product.getSize());
            statement.setString(7, product.getPicture());
            statement.setInt(8, product.getCount());
            statement.setLong(9, product.getCategoryId());
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                result = generatedKey.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("Unable to execute insert query", e);
            throw new DaoException(e.getMessage(), e);
        }
        return result;

    }


    /**
     * Extracts a user from the result set row.
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
                product.setSize(rs.getInt(Fields.PRODUCT__SIZE));
                product.setPicture(rs.getString(Fields.PRODUCT__PICTURE));
                product.setCount(rs.getInt(Fields.PRODUCT__COUNT));
                product.setCategoryId(rs.getInt(Fields.PRODUCT__CATEGORY_ID));
                return product;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }


}
