package com.epam.onlinestore;

import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.exception.ServiceException;
import com.epam.onlinestore.web.command.Command;
import com.epam.onlinestore.web.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 2423353715955164816L;

    private static final Logger log = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        String contextPath = req.getContextPath();
        String path = contextPath + Path.PAGE__ERROR_PAGE;
        try {
            path = command.execute(req, resp);
        } catch (DaoException | ServiceException | SQLException ex) {
            log.error("Error receiving path ",ex);
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        String contextPath = req.getContextPath();
        String path = contextPath + Path.PAGE__ERROR_PAGE;
        try {
            path = contextPath + command.execute(req, resp);
        } catch (DaoException | ServiceException | SQLException ex) {
            log.error("Error receiving path ",ex);
        }
        resp.sendRedirect(resp.encodeRedirectURL(path));
    }


}