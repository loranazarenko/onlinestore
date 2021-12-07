package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.Fields;
import com.epam.onlinestore.dao.LangDAO;
import com.epam.onlinestore.dao.connection.DBManager;
import com.epam.onlinestore.dao.connection.DbConst;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LangDAOImpl implements LangDAO {

    private static final Logger logger = Logger.getLogger(LangDAOImpl.class);

    @Override
    public Language findByName(String name) throws DaoException {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        Language lang = new Language();
        try {
            con = DBManager.getConnection();
            pstm = con.prepareStatement(DbConst.GET_LANG_BY_NAME);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            if (rs.next()) {
                lang.setId(rs.getInt(Fields.ROLE__ID));
                lang.setName(rs.getString(Fields.ROLE__NAME));
            }
        } catch (SQLException e) {
            logger.error("Cant get role name from Database, try later");
        }
        return lang;
    }
}
