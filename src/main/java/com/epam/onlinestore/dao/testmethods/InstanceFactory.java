package com.epam.onlinestore.dao.testmethods;

import java.sql.ResultSet;

public class InstanceFactory {

    public Instance createInstance(String name, ResultSet resultSet) {
        if (name == null || name.isEmpty())
            return null;
        if ("Product".equals(name)) {
            // return new InstanceProduct(resultSet);
        }

        return null;
    }

}
