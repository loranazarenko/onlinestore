package com.epam.onlinestore;

import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.web.command.Command;
import com.epam.onlinestore.web.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 2423353715955164816L;

    private static final Logger log = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        log.trace("command ==> " + commandName);
        Command command = CommandContainer.getCommand(commandName);
        String contextPath = req.getContextPath();
        String path = contextPath + Path.PAGE__ERROR_PAGE;
        try {
            path = command.execute(req, resp);
        } catch (DaoException | ConnectionException ex) {
            log.error("Error receiving address ",ex);
            req.setAttribute("ex", ex);
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        log.trace("commandName ==> " + commandName);

        Command command = CommandContainer.getCommand(commandName);
        log.trace("command ==> " + command);
        String contextPath = req.getContextPath();
        String path = contextPath + Path.PAGE__ERROR_PAGE;
        try {
            path = contextPath + command.execute(req, resp);
        } catch (DaoException | ConnectionException ex) {
            log.error("Error receiving address ",ex);
            req.getSession().setAttribute("ex", ex);
        }
        resp.sendRedirect(resp.encodeRedirectURL(path));
    }

   /* protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException, DaoException {

      /*   log.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        log.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.getCommand(commandName);
        log.trace("Obtained command --> " + command);

        // execute command and get forward address
        String forward = "/error_page.jsp";
        forward = command.execute(request, response);
        log.trace("Forward address --> " + forward);

        log.debug("Controller finished, now go to forward address --> " + forward);

        // if the forward address is not null go to the address
       // if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
        //  disp.forward(request, response);
         response.sendRedirect(forward);
      //  }
    }
            */


}