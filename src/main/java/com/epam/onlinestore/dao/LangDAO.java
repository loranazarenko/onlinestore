package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.exception.DaoException;

public interface LangDAO {

    Language findByName(String name) throws DaoException;
}
