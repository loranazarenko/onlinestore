package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.AccountDAO;
import com.epam.onlinestore.dao.EntityMapper;
import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.dao.connection.DbConst;
import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAOImpl implements AccountDAO {
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public Account createAccount(String login, String password, long role_id) throws SQLException {
        Connection connection = DBManager.getConnection();
        ResultSet keys;
        Account account = new Account();
        try {
            PreparedStatement pstm = connection.prepareStatement(DbConst.INSERT_ACCOUNT, PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 0;
            pstm.setString(++i, escapeForInjection(login));
            pstm.setString(++i, escapeForInjection(password));
            pstm.setLong(++i, role_id);
            if (pstm.executeUpdate() > 0) {
                keys = pstm.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(1);
                    account.setId(id);
                    account.setLogin(login);
                    account.setPassword(password);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            log.error("Cant insert user to Database, try later", e);
        }
        return account;
    }

    @Override
    public boolean block(long account_id) throws SQLException {
        Connection connection = DBManager.getConnection();
        boolean flag = false;
        try {
            PreparedStatement statement = connection.prepareStatement(DbConst.BLOCK_USER);
            statement.setLong(1, account_id);
            flag = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Blocking user error", e);
        }
        return flag;
    }

    @Override
    public boolean unblock(long account_id) throws SQLException {
        Connection connection = DBManager.getConnection();
        boolean flag = false;
        try {
            PreparedStatement statement = connection.prepareStatement(DbConst.UNBLOCK_USER);
            statement.setLong(1, account_id);
            flag = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Unblocking user error", e);
        }
        return flag;
    }

    @Override
    public List<Account> getAll() throws SQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(DbConst.GET_ALL_USERS);
            AccountMapper mapper = new AccountMapper();
            while (resultSet.next()) {
                Account account = mapper.mapRow(resultSet);
                accounts.add(account);
            }
        } catch (Exception ex) {
            log.error("Unable to find all accounts!", ex);
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
            pstm = connection.prepareStatement(DbConst.FIND_USER_BY_LOGIN);
            pstm.setString(1, login);
            rs = pstm.executeQuery();
            AccountMapper mapper = new AccountMapper();
            while (rs.next()) {
                user = mapper.mapRow(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            ;
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
        Connection con;
        PreparedStatement pstm;
        log.error("Username: " + account.getLogin() + " AccountDetailId: " + account.getAccountDetailId());
        try {
            con = DBManager.getConnection();
            pstm = con.prepareStatement(DbConst.UPDATE_ACCOUNT);
    //        pstm.setLong(1, account.getAccountDetailId());
            pstm.setString(1, account.getLogin());
            pstm.setString(2, account.getPassword());
            pstm.setLong(3, account.getRoleId());
            pstm.setLong(4, account.getUserStatuses());
            pstm.setLong(5, account.getId());
            pstm.executeUpdate();
            //   con.commit();
            log.trace("Update done");
        } catch (SQLException e) {
            log.error(e);
        }
        return Optional.of(account);
    }

    @Override
    public Account updateAccountDetailId(Account account) {
        Connection con;
        PreparedStatement pstm;
        log.error("Username: " + account.getLogin() + " AccountDetailId: " + account.getAccountDetailId());
        try {
            con = DBManager.getConnection();
            pstm = con.prepareStatement(DbConst.UPDATE_ACCOUNT_AC_DETAIL_ID);
            pstm.setLong(1, account.getAccountDetailId());
            pstm.setLong(2, account.getId());
            pstm.executeUpdate();
            log.trace("Update done");
        } catch (SQLException e) {
            log.error(e);
        }
        return account;
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
                account.setRoleId(rs.getInt(Fields.ROLE__ID));
                account.setUserStatuses(rs.getInt(Fields.ACCOUNT__USER_STATUSES));
                account.setAccountDetailId(rs.getInt(Fields.ACCOUNT__DETAIL_ID));
                return account;
            } catch (SQLException e) {
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
