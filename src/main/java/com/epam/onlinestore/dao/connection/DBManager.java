package com.epam.onlinestore.dao.connection;

import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    private static DBManager dbManager;
    private static String connectionURL = "";
  //  private static final Connection connection = getConnection();

    public static DBManager getInstance() {

        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public static Connection getConnection(String connectionUrl) {

        dbManager = DBManager.getInstance();
        Connection con = null;
        try {

            con = DriverManager.getConnection(connectionUrl);
            con.setAutoCommit(true);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static Connection getConnection() throws SQLException {
       connectionURL = "jdbc:mysql://localhost:3306/onlineStore?user=root&password=root&useUnicode=true&characterEncoding=cp1251";
       return getConnection(connectionURL);
    }

}
