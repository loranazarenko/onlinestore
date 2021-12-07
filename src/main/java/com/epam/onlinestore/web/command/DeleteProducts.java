package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Controller;
import com.epam.onlinestore.Path;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteProducts extends Command {

    private static final Logger log = Logger.getLogger(Controller.class);
    private ProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws DaoException, IOException, ServletException {

        HttpSession session = req.getSession();
        long itemId = -1;

        if (req.getParameter("itemIds") != null) {
            String[] itemIds = req.getParameterValues("itemIds");
            for (String item : itemIds) {
                itemId = Integer.parseInt(item);
                Product product = new Product();
                List<Long> idx = new ArrayList<>();
                idx.add(itemId);
                product = productService.getProductById(idx).get(0);
                productService.deleteProduct(product);
            }
        }
   /*     Product product = new Product();
        if (itemId != -1) {
            List<Long> idx = new ArrayList<>();
            idx.add(itemId);
            product = productService.getProductById(idx).get(0);
            product.setIs_deleted(true);
            productService.updateProduct(product);
        }*/

        List<Product> listOfProducts = productService.getAllProducts();

        session.setAttribute("listOfProducts", listOfProducts);
        return Path.PAGE__DELETE_PRODUCTS;
    }
}
