package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Path;
import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.service.LogupService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogupCommand extends Command {

    private static final Logger log = LogManager.getLogger(LogupCommand.class);
    private LogupService logupService = new LogupService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, DaoException, ConnectionException {
        log.debug("Start writing user to DB");
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            log.error("Login or password are empty");
            return Path.PAGE__LOGUP;
        }
        if (name == null || name.isEmpty()) {
            log.error("Name are empty");
            return Path.PAGE__LOGUP;
        }

        Account user = logupService.insertUserToBase(login, password, name, email);
        if (user == null) {
            log.error(login + " was already exist");
            return Path.PAGE__LOGUP;
        }
        session.setAttribute("user", user);
        log.debug("Adding user to session: " + user);
        return Path.PAGE__ENTER;
    }

    public void setSingUpService(LogupService logupService) {
        this.logupService = logupService;
    }
}
