package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Path;
import com.epam.onlinestore.dao.impl.AccountDAOImpl;
import com.epam.onlinestore.dao.impl.RoleDAOImpl;
import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, DaoException {

        log.debug("Command starts");

        HttpSession session = request.getSession();

        // obtain login and password from the request
        String login = request.getParameter("login");
        log.trace("Request parameter: loging --> " + login);

        String password = request.getParameter("password");

        // error handler
        String errorMessage = null;
        String forward = Path.PAGE__ENTER;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        Account user = new AccountDAOImpl().getByLogin(login);
        log.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())||user.getUserStatuses()==2) {
            errorMessage = "Cannot find user with such login/password or you are blocked";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            String userRole = null;
            try {
                userRole = new RoleDAOImpl().findById(user.getRoleId());
            } catch (SQLException e) {
                log.error(e);
            }
            log.trace("userRole --> " + userRole);
            session.setAttribute("user", user);
            log.trace("Set the session attribute: user --> " + user);
            session.setAttribute("role", userRole);
            log.trace("Set the session attribute: userRole --> " + userRole);
            log.info("User " + user + " logged as " + (userRole != null ? userRole.toLowerCase() : null));

            if (Objects.equals(userRole, "admin")) {
                return Path.PAGE__ADMIN;
            }
            if (Objects.equals(userRole, "user")) {
                return Path.PAGE__USER;
            }
        }

        log.debug("Command finished");
        return forward;
    }

}