package com.epam.onlinestore.web.command;

import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.service.CartService;
import com.epam.onlinestore.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class QuantityIncDec extends Command {

    private static final Logger log = Logger.getLogger(AddCartCommand.class);
    CartService cartService = new CartService();
    ProductService productService = new ProductService();
    private static final String USER = "user";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoException, SQLException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute(USER);
        long userId = user.getId();
        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = null;
        Map<Product, Integer> userCartProductList = (Map<Product, Integer>) request.getSession().getAttribute("userCartProductList");
        int quantity = -1;
        if (action != null && productId >= 1) {
            if (action.equals("inc")) {
                for (Map.Entry<Product, Integer> c : userCartProductList.entrySet()) {
                    if (c.getKey().getId() == productId && c.getKey().getCount() > 0) {
                        quantity = c.getValue();
                        quantity++;
                        c.setValue(quantity);
                        c.getKey().setCount(c.getKey().getCount() - 1);
                        product = c.getKey();
                    }
                }
            }

            if (action.equals("dec")) {
                for (Map.Entry<Product, Integer> c : userCartProductList.entrySet()) {
                    if (c.getKey().getId() == productId && c.getValue() >= 1) {
                        quantity = c.getValue();
                        quantity--;
                        c.setValue(quantity);
                        c.getKey().setCount(c.getKey().getCount() + 1);
                        product = c.getKey();
                        break;
                    }
                }
            }
        }

        if (productId != 0) {
          /*  int rest = productService.getRestCountProduct(productId);
            List<Long> ids = new ArrayList<>();
            ids.add((long) productId);
            Product product = productService.getProductById(ids).get(0);
            if (rest >= quantity && quantity > 0) {*/
            //   product.setCount(rest - quantity);
            if (product != null) {
                cartService.updateUserBasketProduct(quantity, userId, product.getId());
                productService.updateProduct(product);
            }
            /*      }*/
            int currentCountAll = cartService.countInCartAll(userId);
            session.setAttribute("currentCount", currentCountAll);
        }
        return ("/controller?command=pageCart");
    }

}