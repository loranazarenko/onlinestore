package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Path;
import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.exception.ServiceException;
import com.epam.onlinestore.service.AccountService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandUsers extends Command {
    private static final String ERROR = "errorMessage";
    private static final Logger log = LogManager.getLogger(CommandUsers.class);
    private static final Map<Integer, String> userStatusesMap;
    private AccountService userService = new AccountService();

    static {
        userStatusesMap = new HashMap<>();
        userStatusesMap.put(1, "Active");
        userStatusesMap.put(2, "Disabled");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException, SQLException {
        String path = Path.PAGE__ERROR_PAGE;
        request.getSession().setAttribute("errorMessage", "Invalid parameter action");
        String action = "";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        } else {
            action = "users";
        }
        log.error("Action: " + action);
        if (action.equals("users")) {
            return redirectToUsers(request);
        }
        if (action.equals("update")) {
            return changeUserStatus(request);
        }
        return path;
    }

    private String redirectToUsers(HttpServletRequest request) throws DaoException, SQLException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        if (user.getRoleId() > 1) {
            request.setAttribute(ERROR, "Access denied");
            return Path.PAGE__ERROR_PAGE;
        }
        List<Account> userList = userService.getListOfUsers();
        session.setAttribute("userList", userList);
        int ind = userList.get(0).getUserStatuses();
        session.setAttribute("userStatuses", userStatusesMap);
        return Path.PAGE__ADMIN_USERS;
    }

    private String changeUserStatus(HttpServletRequest request) throws DaoException, SQLException {
        String login = request.getParameter("param");
        Account user = userService.getByLogin(login);

        int status;
        try {
            status = Integer.parseInt(request.getParameter("status"));
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Something wrong with parameters!");
            log.error(e.getMessage());
            return Path.PAGE__ERROR_PAGE;
        }
        if (status == 1) {
            status = 2;
        } else {
            status = 1;
        }
        user.setUserStatuses(status);
        userService.updateAccount(user);
        log.error("Status changed");
        return redirectToUsers(request);
    }


}
