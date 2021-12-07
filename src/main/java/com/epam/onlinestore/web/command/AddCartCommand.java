package com.epam.onlinestore.web.command;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AddCartCommand extends Command {

    private static final Logger log = Logger.getLogger(AddCartCommand.class);
    ProductService productService = new ProductService();

    private static final String USER = "user";
    private static final String PRODUCT_ID = "productId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoException, SQLException {
        HttpSession session = request.getSession();

        long userId = 0;
        Account user = null;
        if (session.getAttribute(USER) != null) {
            user = (Account) session.getAttribute(USER);
            userId = user.getId();
        }
        Map<Product, Integer> temp = null;
        if (session.getAttribute("temp") != null) {
            temp = (Map<Product, Integer>) session.getAttribute("temp");
        } else {
            if (user == null) {
                temp = new LinkedHashMap<>();
            }
        }
        Map<Product, Integer> userCartProductList = null;
        if (session.getAttribute("userCartProductList") != null && user != null) {
            userCartProductList = (Map<Product, Integer>) session.getAttribute("userCartProductList");
        } else {
            if (user != null) {
                userCartProductList = new LinkedHashMap<>();
                if (temp != null) {
                    userCartProductList.putAll(temp);
                    request.getSession().removeAttribute("temp");
                }
            }
        }
        long productId = Long.parseLong(request.getParameter(PRODUCT_ID));
        int rest = productService.getRestCountProduct(productId);
        List<Long> ids = new ArrayList<>();
        ids.add(productId);
        Product product = productService.getProductById(ids).get(0);
        int count = 1;
        if (rest >= count) {
            product.setCount(rest - count);
            productService.updateProduct(product);
        }
        if (userId != 0 && product != null) {
            if (userCartProductList.containsKey(product)) {
                userCartProductList.put(product, userCartProductList.get(product) + count);
            } else {
                userCartProductList.put(product, count);
            }

            int currentCountAll = 0;
            for (Map.Entry<Product, Integer> entry : userCartProductList.entrySet()) {
                currentCountAll += entry.getValue();
            }
            session.setAttribute("currentCount", currentCountAll);
            session.setAttribute("userCartProductList", userCartProductList);
        }
        if (userId == 0 && product != null) {
            if (temp.containsKey(product)) {
                temp.put(product, temp.get(product) + count);
            } else {
                temp.put(product, count);
            }
            int currentCountAll = 0;
            for (Map.Entry<Product, Integer> entry : temp.entrySet()) {
                currentCountAll += entry.getValue();
            }
            session.setAttribute("currentCount", currentCountAll);
            session.setAttribute("temp", temp);
        }

        return ("/controller?command=catalog");
    }

}
