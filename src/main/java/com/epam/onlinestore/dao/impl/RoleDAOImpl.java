package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.RoleDAO;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.dao.connection.DbConst;
import com.epam.onlinestore.entity.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoleDAOImpl implements RoleDAO {
    private static final Logger logger = Logger.getLogger(RoleDAOImpl.class);

    @Override
    public Optional<Role> findByName(String name) {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        Role role = new Role();
        try {
            con = DBManager.getConnection();
            pstm = con.prepareStatement(DbConst.GET_ROLE_BY_NAME);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            if (rs.next()) {
                role.setId(rs.getInt(Fields.ROLE__ID));
                role.setName(rs.getString(Fields.ROLE__NAME));
            }
        } catch (SQLException e) {
            logger.error("Cant get role name from Database, try later");
        }
        return Optional.of(role);
    }

    public int findIdByName(String name) throws SQLException {
        int roleId = 0;
        Connection con = DBManager.getConnection();
        PreparedStatement pstm;
        ResultSet rs;
        try {
            pstm = con.prepareStatement(DbConst.GET_ROLE_BY_ID);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            while (rs.next()) {
                roleId = rs.getInt(Fields.ROLE__ID);
            }
        } catch (SQLException e) {
            logger.error("Cant get role name from Database, try later");
        }
        return roleId;
    }

    public String findById(long id) throws SQLException {
        String roleName = "";
        Connection con = DBManager.getConnection();
        PreparedStatement pstm;
        ResultSet rs;
        try {
            pstm = con.prepareStatement(DbConst.GET_ROLE_BY_ID);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                roleName = rs.getString(Fields.ROLE__NAME);
            }
            con.commit();
        } catch (SQLException e) {
            logger.error("Cant get role name from Database, try later");
        }
        return roleName;
    }
}
