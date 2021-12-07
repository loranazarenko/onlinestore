package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Path;
import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class PageCartCommand extends Command {

    private static final Logger log = Logger.getLogger(PageCartCommand.class);
    ProductService productService = new ProductService();
    private static final String USER = "user";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoException, SQLException {
        HttpSession session = request.getSession();
        Map<Product, Integer> userCartProductList = new LinkedHashMap<>();
        Map<Product, Integer> temp = new LinkedHashMap<>();
        Map<Product, Integer> cart = new LinkedHashMap<>();

        long userId = 0;
        Account user = null;
        if (session.getAttribute("user") != null) {
            user = (Account) session.getAttribute(USER);
            userId = user.getId();
            cart = (Map<Product, Integer>) session.getAttribute("userCartProductList");
        } else {
            cart = (Map<Product, Integer>) session.getAttribute("temp");
        }

        if (user != null) {
            if (temp.size() != 0) {
                cart.putAll(temp);
                session.removeAttribute("temp");
            }
        }

        String action = "";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        int productId = 0;
        if (request.getParameter("id") != null) {
            productId = Integer.parseInt(request.getParameter("id"));
        }

        Product product;

        if (!Objects.equals(action, "") && productId >= 1) {
            List<Long> ids = new ArrayList<>();
            ids.add((long) productId);
            product = productService.getProductById(ids).get(0);

            if (action.equals("inc")) {
                if (product.getCount() - 1 >= 0) {
                    cart.put(product, cart.get(product) + 1);
                    product.setCount(product.getCount() - 1);
                }
            }
            if (action.equals("dec")) {
                if (cart.get(product) - 1 >= 0) {
                    cart.put(product, cart.get(product) - 1);
                    product.setCount(product.getCount() + 1);
                }
            }
            if (action.equals("delete")) {
                product.setCount(product.getCount() + cart.get(product));
                cart.remove(product);
            }

            productService.updateProduct(product);
        }

        if (cart != null) {
            int currentCountAll = 0;
            double totalPrice = 0;
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                totalPrice += entry.getKey().getPrice() * entry.getValue();
                currentCountAll += entry.getValue();
            }
            session.setAttribute("currentCount", currentCountAll);
            session.setAttribute("totalPrice", totalPrice);
            session.setAttribute("cart", cart);

            if (userId == 0) {
                temp.clear();
                temp.putAll(cart);
                session.setAttribute("temp", temp);
            }
            if (userId != 0) {
                userCartProductList.clear();
                userCartProductList.putAll(cart);
                session.setAttribute("userCartProductList", userCartProductList);
            }
        }

        return Path.PAGE_CART;
    }
}
