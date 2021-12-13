package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Path;
import com.epam.onlinestore.dao.impl.StatusDAOImpl;
import com.epam.onlinestore.entity.Account;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.entity.Receipt;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.service.ProductService;
import com.epam.onlinestore.service.ReceiptService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserOrdersCommand extends Command {

    private static final Logger logger = Logger.getLogger(UserOrdersCommand.class);
    private static final ReceiptService receiptService = new ReceiptService();
    ProductService productService = new ProductService();
    StatusDAOImpl statusDAO = new StatusDAOImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoException, SQLException {
        HttpSession session = request.getSession();

        long userId = -1;
        if (request.getParameter("userid") != null) {
            userId = Integer.parseInt(request.getParameter("userid"));
        } else {
            userId = ((Account) session.getAttribute("user")).getId();
        }
        long receiptId = -1;
        if (request.getParameter("receiptId") != null) {
            receiptId = Integer.parseInt(request.getParameter("receiptId"));
        }
        String status = "";
        if (request.getParameter("status") != null) {
            status = request.getParameter("status");
        }

        List<Product> productList = receiptService.getReceiptProductsWithAmount(receiptId);
        for (Product product : productList) {
            if (status.equals("canceled")) {
                product.setCount(product.getCount() + 1);
                productService.updateProduct(product);
            }
        }

        ReceiptService receiptService = new ReceiptService();
        if (receiptId != -1) {
            long statusId = statusDAO.getStatusIdByName(status);
            receiptService.changeStatusReceipt(receiptId, statusId);
        }

        List<Receipt> receipts = receiptService.getReceiptsByUser(userId);
        Map<Receipt, String> receiptsStatuses = new LinkedHashMap<>();
        for (Receipt rec : receipts) {
            String statusDao = statusDAO.getStatusNameById((int) rec.getStatusId());
            receiptsStatuses.put(rec, statusDao);
        }
        session.setAttribute("receiptsStatuses", receiptsStatuses);

        return Path.PAGE__USER_ORDERS;
    }

}

