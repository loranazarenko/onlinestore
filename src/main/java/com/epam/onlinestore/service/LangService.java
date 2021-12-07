package com.epam.onlinestore.service;

import com.epam.onlinestore.Controller;
import com.epam.onlinestore.dao.impl.LangDAOImpl;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.exception.DaoException;
import org.apache.log4j.Logger;

public class LangService {
    private static final Logger log = Logger.getLogger(LangService.class);
    private LangDAOImpl langDAO;

    {
        langDAO = new LangDAOImpl();
    }

    public Language findByName(String name) throws DaoException {
        return langDAO.findByName(name);
    }

}
