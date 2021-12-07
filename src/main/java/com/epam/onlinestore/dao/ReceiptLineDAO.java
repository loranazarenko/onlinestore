package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.entity.Receipt;

import java.util.List;
import java.util.Map;

public interface ReceiptLineDAO {

    void setProductsForReceipt(Receipt receipt, Map<Integer, Product> map);

    List<Product> getReceiptProductsWithAmount(Receipt receipt);

    void insertReceiptWithLine(long receiptId, Map<Product, Integer> mapProductsWithCount);

}
