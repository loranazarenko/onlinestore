package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Path;
import com.epam.onlinestore.dao.impl.StatusDAOImpl;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AdminUserOrdersCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminUserOrdersCommand.class);

    StatusDAOImpl statusDAO = new StatusDAOImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoException, SQLException {
        HttpSession session = request.getSession();

        long receiptId = -1;
        if (request.getParameter("receiptId") != null) {
            receiptId = Integer.parseInt(request.getParameter("receiptId"));
        }

        String status = "";
        if (request.getParameter("status") != null) {
            status = request.getParameter("status");
        }

        ReceiptService receiptService = new ReceiptService();
        if (receiptId != -1) {
            if (!Objects.equals(status, "deleted")) {
                long statusId = statusDAO.getStatusIdByName(status);
                receiptService.changeStatusReceipt(receiptId, statusId);
            }else if(status.equals("deleted")){
                receiptService.removeReceiptById(receiptId);
            }
        }

        List<Receipt> receipts = receiptService.getAllReceipts();
        Map<Receipt, String> receiptsStatuses = new LinkedHashMap<>();
        for(Receipt rec: receipts) {
            String statusDao = statusDAO.getStatusNameById((int) rec.getStatusId());
            receiptsStatuses.put(rec, statusDao);
        }
        session.setAttribute("allReceiptsStatuses", receiptsStatuses);

        return Path.PAGE__ADMIN_USER_ORDERS;
    }

}

