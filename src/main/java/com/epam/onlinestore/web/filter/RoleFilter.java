package com.epam.onlinestore.web.filter;

import com.epam.onlinestore.Path;
import com.epam.onlinestore.entity.Account;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute("user");
        if (user != null && user.getRoleId() < 3) {
            filterChain.doFilter(request, response);
        } else {
            session.setAttribute("errorMessage", "Access denied");
            RequestDispatcher dispatcher = req.getRequestDispatcher(Path.PAGE__ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }


}
