package com.epam.onlinestore;

import com.epam.onlinestore.dao.impl.ProductDAOImpl;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/")
public class Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductDAOImpl productDAO = null;
        try {
            productDAO = new ProductDAOImpl();
        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
        }
        List<Product> products = null;
        try {
            products = productDAO.findAll();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        request.setAttribute("products", products);

        getServletContext().getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);


    }
}