package com.epam.onlinestore.dao.connection;

import com.epam.onlinestore.dao.ProductDAO;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DAOFactory {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_CONNECTION_PATH = "dbСonnection.properties";

    public static DAOFactory getInstance(String name) throws IOException, ConnectionException {

        Properties dbProperties = new Properties();
        dbProperties.load(DAOFactory.class.getClassLoader().getResourceAsStream(DB_CONNECTION_PATH));
        String url = dbProperties.getProperty(DB_URL);
        String driverClassName = dbProperties.getProperty(DB_DRIVER);
        String password = dbProperties.getProperty(DB_PASSWORD);
        String username = dbProperties.getProperty(DB_USER);
        DAOFactory instance;

        // If driver is specified, then load it to let it register itself with DriverManager.
        if (driverClassName != null) {
            try {
                Class.forName(driverClassName);
            } catch (ClassNotFoundException e) {
                throw new ConnectionException(
                        "Driver class '" + driverClassName + "' is missing in classpath.", e);
            }
            instance = new DriverManagerDAOFactory(url, username, password);
        }

        // Else assume URL as DataSource URL and lookup it in the JNDI.
        else {
            DataSource dataSource;
            try {
                dataSource = (DataSource) new InitialContext().lookup(url);
            } catch (NamingException e) {
                throw new ConnectionException(
                        "DataSource '" + url + "' is missing in JNDI.", e);
            }
            if (username != null) {
                instance = new DataSourceWithLoginDAOFactory(dataSource, username, password);
            } else {
                instance = new DataSourceDAOFactory(dataSource);
            }
        }
        return instance;
    }

    public abstract Connection getConnection() throws SQLException;

    // DAO implementation getters -----------------------------------------------------------------

    /**
     * Returns the User DAO associated with the current DAOFactory.
     *
     * @return The User DAO associated with the current DAOFactory.
     */
    public ProductDAO getProductDAO() throws IOException, ConnectionException {
        //return new ProductDAOImpl(this);
        return null;
    }
}

// Default DAOFactory implementations -------------------------------------------------------------

/**
 * The DriverManager based DAOFactory.
 */
class DriverManagerDAOFactory extends DAOFactory {
    private String url;
    private String username;
    private String password;

    DriverManagerDAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

class DataSourceDAOFactory extends DAOFactory {
    private DataSource dataSource;

    DataSourceDAOFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

/**
 * The DataSource-with-Login based DAOFactory.
 */
class DataSourceWithLoginDAOFactory extends DAOFactory {
    private DataSource dataSource;
    private String username;
    private String password;

    DataSourceWithLoginDAOFactory(DataSource dataSource, String username, String password) {
        this.dataSource = dataSource;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection(username, password);
    }
}
