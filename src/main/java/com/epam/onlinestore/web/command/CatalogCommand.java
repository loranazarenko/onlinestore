package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Controller;
import com.epam.onlinestore.Path;
import com.epam.onlinestore.dao.impl.ProductDAOImpl;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CatalogCommand extends Command {

    private static final Logger log = Logger.getLogger(Controller.class);
    private ProductService productService = new ProductService(new ProductDAOImpl());

    public CatalogCommand() throws IOException, ConnectionException {
    }

    public String execute(HttpServletRequest req, HttpServletResponse res) throws DaoException {
        log.error("In get Catalog");
        HttpSession session = req.getSession();
        List<Product> listOfProducts = productService.getAllProducts();
        session.setAttribute("listOfProducts", listOfProducts);
        return Path.CATALOG;
    }

    public void setCatalogService(ProductService productService) {
        this.productService = productService;
    }
}