package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.StatusDAO;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.entity.Status;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class StatusDAOImpl implements StatusDAO {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    private static final String GET_STATUS_ID = "SELECT id from status where name=?";
    private static final String GET_STATUS_NAME = "SELECT name from status where id=?";

    @Override
    public Optional<Status> findByName(String name) throws DaoException {
        return Optional.empty();
    }

    public int getStatusByName(String statusName) throws DaoException {
        DBManager dbManager = DBManager.getInstance();
        int statusId = 0;
        Connection con = dbManager.getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement pstm = con.prepareStatement(GET_STATUS_ID);
            pstm.setString(1, statusName);
            rs = pstm.executeQuery();
            while (rs.next()) {
                //     statusId = rs.getInt(Fields.ENTITY_ID);
            }
            con.commit();
        } catch (SQLException e) {
            throw new DaoException("Cant get order status from Database, try later", e);
        }
        return statusId;
    }

    public String getStatusNameById(int id) throws DaoException {
        DBManager dbManager = DBManager.getInstance();
        String statusName = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            try (PreparedStatement pstm = con.prepareStatement(GET_STATUS_NAME)) {
                pstm.setLong(1, id);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    //    statusName = rs.getString(Fields.STATUS_NAME);
                }
                con.commit();
            }
        } catch (SQLException e) {
            throw new DaoException("Cant get status name from Database, try later", e);
        }
        return statusName;
    }


}
