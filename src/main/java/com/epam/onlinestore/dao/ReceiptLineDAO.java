package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.entity.Receipt;

import java.util.List;
import java.util.Map;

public interface ReceiptLineDAO {

    public void setProductsForReceipt(Receipt receipt, Map<Integer, Product> map);

    public List<Product> getReceiptProductsWithAmount(Receipt receipt);

}
