package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Path;
import com.epam.onlinestore.dao.ProductLanguageDAO;
import com.epam.onlinestore.dao.impl.ProductLanguageDAOImpl;
import com.epam.onlinestore.entity.Category;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.service.CategoryService;
import com.epam.onlinestore.service.LangService;
import com.epam.onlinestore.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.*;

public class EditProductCommand extends Command {
    private static final Logger log = Logger.getLogger(EditProductCommand.class);
    private static final String PRODUCT_ID = "productId";
    ProductLanguageDAO productLanguageDAO = new ProductLanguageDAOImpl();
    LangService langService = new LangService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("cp1251");
        response.setCharacterEncoding("cp1251");

        HttpSession session = request.getSession();
        Object userRole = session.getAttribute("role");
        if (Objects.equals(userRole, "user")) {
            return Path.PAGE__ERROR_PAGE;
        }

        try {
            CategoryService categoryService = new CategoryService();
            List<Category> categories = categoryService.getAllCategories();
            session.setAttribute("categories", categories);
            long productId = Long.parseLong(request.getParameter(PRODUCT_ID));
            ProductService productService = new ProductService();
            List<Long> ids = new ArrayList<>();
            ids.add(productId);
            List<Product> products = productService.getProductById(ids);
            Language langRu = langService.findByName("ru");
            String description = productLanguageDAO.getTranslationForProduct(products.get(0),langRu);
            if (products.size() > 0) {
                session.setAttribute("product", products.get(0));
                Category category = categoryService.getCategoryById(products.get(0).getCategoryId());
                session.setAttribute("category", category);
                session.setAttribute("langRu", description);
            }
        } catch (DaoException | SQLException e) {
            log.error("Unable to find product!", e);
        }
        return Path.PAGE__SINGLE_PRODUCT;
    }

}
