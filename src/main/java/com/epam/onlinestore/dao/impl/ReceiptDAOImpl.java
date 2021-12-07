package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.EntityMapper;
import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.ReceiptDAO;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.dao.connection.DbConst;
import com.epam.onlinestore.entity.Receipt;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAOImpl implements ReceiptDAO {
    private static final Logger logger = Logger.getLogger(ReceiptDAOImpl.class);


    @Override
    public List<Receipt> findByUser(long userId) throws DaoException {
        List<Receipt> entities = new ArrayList<>();
        Connection connection;
        try {
            connection = DBManager.getConnection();
            PreparedStatement state = connection.prepareStatement(DbConst.FIND_RECEIPTS_BY_ACCOUNT_ID);
            state.setLong(1, userId);
            ResultSet resultSet = state.executeQuery();
            ReceiptMapper mapper = new ReceiptMapper();
            while (resultSet.next()) {
                entities.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Unable to execute query", e);
            throw new DaoException(e.getMessage(), e);
        }
        return entities;

    }

    @Override
    public long addReceipt(Receipt receipt, String status) throws DaoException, SQLException {
        Connection connection;
        ResultSet keys;
        long statusId = new StatusDAOImpl().getStatusIdByName(status);
        int i = 0;
        try {
            connection = DBManager.getConnection();
            try (PreparedStatement pstm = connection.prepareStatement(DbConst.ADD_RECEIPT,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstm.setString(++i, receipt.getDescription());
                pstm.setDouble(++i, receipt.getTotal());
                pstm.setLong(++i, statusId);
                pstm.setLong(++i, receipt.getAccountId());
                if (pstm.executeUpdate() > 0) {
                    keys = pstm.getGeneratedKeys();
                    if (keys.next()) {
                        int id = keys.getInt(1);
                        receipt.setId(id);
                        receipt.setStatusId(statusId);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Cant create order. Database not response, try later.", e);
        }
        return receipt.getId();
    }

    @Override
    public int getReceiptCount() {
        return 0;
    }

    @Override
    public List<Receipt> getByUser(int userId) {
        return null;
    }

    @Override
    public Receipt getById(int orderId) {
        return null;
    }

    @Override
    public void removeReceiptById(long orderId) throws SQLException {
        Connection connection = DBManager.getConnection();
        try (PreparedStatement st = connection.prepareStatement(DbConst.DELETE_RECEIPT)) {
            st.setLong(1, orderId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void changeStatusReceipt(long orderId, long statusId) throws SQLException {
        Connection connection = DBManager.getConnection();
        try (PreparedStatement st = connection.prepareStatement(DbConst.UPDATE_STATUS_RECEIPT)) {
            st.setLong(1, statusId);
            st.setLong(2, orderId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Receipt> getAllReceipts() {
        List<Receipt> entities = new ArrayList<>();
        Connection connection;
        try {
            connection = DBManager.getConnection();
            PreparedStatement state = connection.prepareStatement(DbConst.FIND_ALL_RECEIPTS);
            ResultSet resultSet = state.executeQuery();
            ReceiptMapper mapper = new ReceiptMapper();
            while (resultSet.next()) {
                entities.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Unable to execute query", e);
        }
        return entities;
    }

    private static class ReceiptMapper implements EntityMapper<Receipt> {

        public Receipt mapRow(ResultSet rs) {
            try {
                Receipt receipt = new Receipt();
                receipt.setId(rs.getLong(Fields.RECEIPT_ID));
                receipt.setDescription(rs.getString(Fields.RECEIPT_DESCRIPTION));
                receipt.setTotal(rs.getDouble(Fields.RECEIPT_TOTAL));
                receipt.setStatusId(rs.getLong(Fields.RECEIPT_STATUS_ID));
                receipt.setAccountId(rs.getLong(Fields.RECEIPT_ACCOUNT_ID));
                return receipt;
            } catch (SQLException e) {
                logger.error(e);
                throw new IllegalStateException(e);
            }
        }

    }

}
