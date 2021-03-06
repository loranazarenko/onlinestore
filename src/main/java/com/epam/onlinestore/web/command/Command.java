package com.epam.onlinestore.web.command;

import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author D.Kolesnikov
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 8879403039606311780L;

    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, DaoException, SQLException, ServiceException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}