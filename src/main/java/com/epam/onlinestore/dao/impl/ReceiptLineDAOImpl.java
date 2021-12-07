package com.epam.onlinestore.dao.impl;

import com.epam.onlinestore.dao.ReceiptLineDAO;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.entity.Receipt;
import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class ReceiptLineDAOImpl implements ReceiptLineDAO {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    @Override
    public void setProductsForReceipt(Receipt receipt, Map<Integer, Product> map) {

    }

    @Override
    public List<Product> getReceiptProductsWithAmount(Receipt receipt) {
        return null;
    }
}
