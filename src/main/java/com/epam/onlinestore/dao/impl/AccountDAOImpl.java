package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.AccountDAO;
import com.epam.onlinestore.dao.EntityMapper;
import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAOImpl implements AccountDAO {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
//,account_detail_id,?
    private static final String INSERT_ACCOUNT = "insert into account (login, password, role_id) values (?,?,?)";
    private static final String GET_ALL_USERS = "SELECT * FROM account ORDER BY id;";
    private static final String FIND_USER_BY_ID = "select * from account where id=?";
    private static final String FIND_USER_BY_LOGIN = "select * from account where login=?";


    @Override
    public boolean createAccount(String login, String password, long role_id) throws DaoException, ConnectionException {
        Connection connection = DBManager.getConnection();
        ResultSet keys;
        boolean result = false;
        try {
            PreparedStatement pstm = connection.prepareStatement(INSERT_ACCOUNT, PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 0;
            pstm.setString(++i, escapeForInjection(login));
            pstm.setString(++i, escapeForInjection(password));
            pstm.setLong(++i, role_id);
            if (pstm.executeUpdate() > 0) {
                keys = pstm.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(1);
                    Account account = new Account();
                    account.setId(id);
                    account.setLogin(login);
                    account.setPassword(password);
                }
            }
            connection.commit();
            result = true;
        } catch (SQLException e) {
            log.error("Cant insert user to Database, try later", e);
       //     throw new DaoException("Cant insert user to Database, try later", e);
        }
        return result;
    }

    @Override
    public List<Account> getAll() throws DaoException {
        ArrayList<Account> accounts = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                Account account = new Account(id, login, password);
                accounts.add(account);
            }
        } catch (Exception ex) {
            log.error("Unable to find all accounts!", ex);
           // throw new DaoException(ex.getMessage(), ex);
        }
        return accounts;
    }

    @Override
    public Account getByLogin(String login) throws DaoException {
        DBManager dbManager = DBManager.getInstance();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Account user = null;
        try {
            log.error("In getByLogin");
            connection = DBManager.getConnection();
            pstm = connection.prepareStatement(FIND_USER_BY_LOGIN);
            pstm.setString(1, login);
            rs = pstm.executeQuery();
            AccountMapper mapper = new AccountMapper();
            while (rs.next()) {
                user = mapper.mapRow(rs);
            }
            connection.commit();
        } catch (SQLException e) {
          //  throw new DaoException("Cant get user from Database, try later", e);
            log.error("Cant get user from Database, try later");
        }
        return user;

    }

    @Override
    public Optional<Account> getById(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<Account> updateAccount(Account account) throws DaoException {
        return Optional.empty();
    }

    /**
     * Extracts a account from the result set row.
     */
    private static class AccountMapper implements EntityMapper<Account> {
        @Override
        public Account mapRow(ResultSet rs) {
            try {
                Account account = new Account();
                account.setId(rs.getLong(Fields.ACCOUNT__ID));
                account.setLogin(rs.getString(Fields.ACCOUNT__LOGIN));
                account.setPassword(rs.getString(Fields.ACCOUNT__PASSWORD));
                account.setRoleId(rs.getInt(Fields.ROLE_ID));
                account.setAccountDetailId(new AccountDetailDAOImpl().getAccountDetailById(account.getId()).getId());
                return account;
            } catch (SQLException | DaoException e) {
                log.error(e.getMessage());
                return null;
            }
        }
    }

    static String escapeForInjection(String param) {
        return param.replace("!", "!!").
                replace("<", "!<").
                replace(">", "!").
                replace("%", "!%").
                replace("_", "!_").
                replace("[", "![");
    }
}
