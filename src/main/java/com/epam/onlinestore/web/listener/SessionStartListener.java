package com.epam.onlinestore.web.listener;

import com.epam.onlinestore.dao.impl.ProductDAOImpl;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;
import java.util.List;

public class SessionStartListener implements HttpSessionListener {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    @Override
    public void sessionCreated(HttpSessionEvent session) {

        System.out.println("Session started" + session.getSession().getId());
        List<Product> listOfProducts = null;

        try {
            listOfProducts = ProductDAOImpl.getInstance().findAll();
        } catch (IOException | ConnectionException | DaoException e) {
            System.out.println(e.getMessage());
        }

        session.getSession().setAttribute("listOfProducts", listOfProducts);
        session.getSession().setAttribute("language", "ru");
        session.getSession().setAttribute("productsIsSortedByColor", false);
        session.getSession().setAttribute("sortedByPrice", false);
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent session) {
        System.out.println("Session stopped" + session.getSession().getId());

    }
}
