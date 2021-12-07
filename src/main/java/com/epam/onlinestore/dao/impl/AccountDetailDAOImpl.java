package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.AccountDetailDAO;
import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.entity.AccountDetail;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDetailDAOImpl implements AccountDetailDAO {
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    private static final String GET_USER_BY_USERNAME = "SELECT id, name, email, account_id FROM account_detail INNER JOIN users u on account_detail.accountId = u.id WHERE login=?";
    private static final String GET_USER_BY_ID = "SELECT * FROM account_detail WHERE id=?";
    private static final String INSERT_USER_INFO = "INSERT INTO account_detail(name,email) VALUES (?,?)";
    private static final String UPDATE_USER_INFO = "UPDATE account_detail SET account_id =? WHERE id=?";

    @Override
    public AccountDetail getAccountDetailById(long id) throws DaoException {
        AccountDetail userInfo = new AccountDetail();
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        try {
            con = DBManager.getConnection();
            pstm = con.prepareStatement(GET_USER_BY_ID);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                userInfo = mapRow(rs);
            }
        } catch (SQLException e) {
            log.error("Cant get user info from Database, try later", e);
        }
        return userInfo;
    }

    @Override
    public void insertAccountDetail(AccountDetail accountDetail) {
        Connection connection;
        int i = 0;
        try {
            connection = DBManager.getConnection();
            try (PreparedStatement pstm = connection.prepareStatement(INSERT_USER_INFO,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstm.setString(++i, accountDetail.getName());
                pstm.setString(++i, accountDetail.getEmail());
                pstm.executeUpdate();
                ResultSet keys = pstm.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(1);
                    accountDetail.setId(id);
                }
            }
        } catch (SQLException e) {
            log.error("Cant insert user info to Database, try later", e);
        }
    }

    public void updateAccountDetail(AccountDetail accountDetail) {
        Connection connection = DBManager.getConnection();
        try (PreparedStatement st = connection.prepareStatement(UPDATE_USER_INFO)) {
            st.setLong(1, accountDetail.getAccountId());
            st.setLong(2, accountDetail.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            log.error("Cant update user info to Database, try later", e);
        }
    }

    private AccountDetail mapRow(ResultSet rs) {
        try {
            AccountDetail userInfo = new AccountDetail();
            userInfo.setId(rs.getLong(Fields.ACCOUNT__DETAIL_ID));
            userInfo.setName(rs.getString(Fields.ACCOUNT__DETAIL_NAME));
            userInfo.setEmail(rs.getString(Fields.ACCOUNT__DETAIL_EMAIL));
            userInfo.setAccountId((int) rs.getLong(Fields.ACCOUNT__ACCOUNT__DETAIL_ID));
            return userInfo;
        } catch (SQLException e) {
            log.error("Cant create user, try later", e);
            return null;
        }
    }

}
