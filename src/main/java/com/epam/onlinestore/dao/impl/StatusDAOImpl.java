package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.StatusDAO;
import com.epam.onlinestore.dao.connection.DBManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusDAOImpl implements StatusDAO {
    private static final Logger logger = Logger.getLogger(StatusDAOImpl.class);

    private static final String GET_STATUS_ID = "SELECT id from status where name=?";
    private static final String GET_STATUS_NAME = "SELECT name from status where id=?";

    @Override
    public long getStatusIdByName(String statusName) throws SQLException {
        long statusId = 0;
        Connection con = DBManager.getConnection();
        ResultSet rs;
        try {
            PreparedStatement pstm = con.prepareStatement(GET_STATUS_ID);
            pstm.setString(1, statusName);
            rs = pstm.executeQuery();
            while (rs.next()) {
                statusId = rs.getLong(Fields.STATUS_ID);
            }
        } catch (SQLException e) {
            logger.error("Cant get order status from Database, try later", e);
        }
        return statusId;
    }

    @Override
    public String getStatusNameById(long id) throws SQLException {
        String statusName = null;
        Connection con = DBManager.getConnection();
        ResultSet rs;
        try {
            try (PreparedStatement pstm = con.prepareStatement(GET_STATUS_NAME)) {
                pstm.setLong(1, id);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    statusName = rs.getString(Fields.STATUS_NAME);
                }
            }
        } catch (SQLException e) {
            logger.error("Cant get status name from Database, try later", e);
        }
        return statusName;
    }


}
