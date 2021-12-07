package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Path;
import com.epam.onlinestore.entity.Category;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.service.CategoryService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddProduct extends Command {

    private static final Logger log = Logger.getLogger(AddProduct.class);
    private static final String CATEGORIES = "categories";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws DaoException, IOException, ServletException, SQLException {
        req.setCharacterEncoding("cp1251");
        res.setCharacterEncoding("cp1251");

        HttpSession session = req.getSession();
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        session.setAttribute(CATEGORIES, categories);

        return Path.PAGE__ADD_PRODUCT;
    }

}
