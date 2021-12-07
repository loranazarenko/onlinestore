package com.epam.onlinestore.dao.testmethods;

import com.epam.onlinestore.dao.connection.DAOFactory;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.io.IOException;

public class QueryExecutor<E> {
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    DAOFactory daoFactory = DAOFactory.getInstance("javabase.jdbc");

    public QueryExecutor() throws IOException, ConnectionException {
    }

  /*  protected List<E> executeQuery(String query, Object... params) throws DaoException {
        List<E> entities = new ArrayList<>();
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            entities = createEntitiesList(resultSet);
        } catch (SQLException e) {
            logger.error("Unable to execute query", e);
            throw new DaoException(e.getMessage(), e);
        }
        return entities;
    }

    protected Optional<E> executeQueryForSingleResult(String query, Object... params) throws DaoException {
        List<E> items = executeQuery(query, params);
        if (items.isEmpty()) {
            return Optional.empty();
        }

        if (!(items.size() == 1)) {
            return Optional.empty();
        }

        return Optional.of(items.get(0));
    }

    protected long executeInsertQuery(String query, Object... params) throws DaoException {
        long result = 0;
        try (PreparedStatement statement = createStatement(query, params)) {
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

    protected void executeUpdateQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to execute update query", e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws DaoException {
        try {
            Connection connection = daoFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        } catch (SQLException e) {
            logger.error("Unable to create statement!", e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    /*private List<E> createEntitiesList(ResultSet resultSet) throws DaoException {
        List<E> entities = new ArrayList<>();
        entities.get(0).getClass();
        try {
            while (resultSet.next()) {
                E entity = extract(E,resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            logger.error("Unable to create entity list!", e);
            throw new DaoException(e.getMessage(), e);
        }
        return entities;
    }

    private E extract(E  ,ResultSet resultSet) {
        InstanceFactory instanceFactory = new InstanceFactory();
        E instance = instanceFactory.createInstance(name, resultSet);
        return instance;
    }*/
}
