package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Path;
import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.entity.Receipt;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.service.ReceiptService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MakeReceiptCommand extends Command {

    private static final Logger logger = Logger.getLogger(MakeReceiptCommand.class);
    private static final ReceiptService receiptService = new ReceiptService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoException, SQLException {
        HttpSession session = request.getSession();
        long userId = ((Account) session.getAttribute("user")).getId();
        if (session.getAttribute("user") == null) {
            logger.error("To shop you need to register!!!");
            return Path.PAGE__SIGNUP;
        }
        session.removeAttribute("userCartProductList");
        session.removeAttribute("temp");
        Map<Product, Integer> userCart = new LinkedHashMap<>();
        if (session.getAttribute("cart") != null) {
            userCart = (Map<Product, Integer>) session.getAttribute("cart");
        }
        Map<Product, Integer> userOrder = new LinkedHashMap<>(userCart);
        userCart.clear();
        session.removeAttribute("cart");

        double totalPrice = (double) session.getAttribute("totalPrice");
        Receipt receipt = new Receipt();
        receipt.setAccountId(userId);
        receipt.setTotal(totalPrice);
        receipt.setDescription("New Receipt  " + receipt.getId());
        logger.error("Order: " + receipt);
        receiptService.addReceipt(receipt, userOrder, "registered");
        session.setAttribute("order", receipt);
        session.setAttribute("orderItems", userOrder);

        session.setAttribute("currentCount", 0);

        return Path.PAGE_ORDER;
    }

}

