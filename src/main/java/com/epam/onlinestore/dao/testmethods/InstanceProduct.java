package com.epam.onlinestore.dao.testmethods;

import com.epam.onlinestore.entity.Product;

public class InstanceProduct implements Instance {

    private static Product productInstance;

    public void getInstance() {
        if (productInstance == null) {
            productInstance = new Product();
        }
    }


}
