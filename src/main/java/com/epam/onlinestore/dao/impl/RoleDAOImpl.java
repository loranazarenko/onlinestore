package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.RoleDAO;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.entity.Role;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoleDAOImpl implements RoleDAO {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    private static final String GET_ROLE_BY_NAME = "select name from role where id=?";
    private static final String GET_ROLE_BY_ID = "select id from role where name=?";

    private DBManager dbManager;

    @Override
    public Optional<Role> findByName(String name) {
        return null;
    }

    public int findIdByName(String name) {
        dbManager = DBManager.getInstance();
        int roleId = 0;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstm = con.prepareStatement(GET_ROLE_BY_ID);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            while (rs.next()) {
                roleId = rs.getInt(Fields.ROLE_ID);
            }
        } catch (SQLException e) {
            logger.error("Cant get role name from Database, try later");
        //    throw new ConnectionException("Cant get role name from Database, try later", e);
        }
        return roleId;
    }

    public String findById(long id) throws ConnectionException {
        dbManager = DBManager.getInstance();
        String roleName = "";
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstm = con.prepareStatement(GET_ROLE_BY_NAME);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                roleName = rs.getString(Fields.ROLE__NAME);
            }
            con.commit();
        } catch (SQLException e) {
            //  dbManager.rollback(con);
           // throw new ConnectionException("Cant get role name from Database, try later", e);
            logger.error("Cant get role name from Database, try later");
        }
        return roleName;
    }
}
