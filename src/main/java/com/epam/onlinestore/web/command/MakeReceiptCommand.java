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

        Map<Product, Integer> userCart = new LinkedHashMap<>();
        if (session.getAttribute("cart") != null) {
            userCart = (Map<Product, Integer>) session.getAttribute("cart");
        }

        double totalPrice = (double) session.getAttribute("totalPrice");
        Receipt receipt = new Receipt();
        receipt.setAccountId(userId);
        receipt.setTotal(totalPrice);
        receipt.setDescription("New Receipt  " + receipt.getId());
        logger.error("Order: " + receipt);
        receiptService.addReceipt(receipt, userCart, "registered");
        session.setAttribute("order", receipt);
        session.setAttribute("orderItems", userCart);

        return Path.PAGE_ORDER;
    }

}

