package com.epam.onlinestore.web.command;

import com.epam.onlinestore.Controller;
import com.epam.onlinestore.Path;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.service.LangService;
import com.epam.onlinestore.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CatalogCommand extends Command {

    private static final Logger log = Logger.getLogger(Controller.class);
    private ProductService productService = new ProductService();
    private LangService langService = new LangService();

    public String execute(HttpServletRequest req, HttpServletResponse res) throws DaoException, IOException, ServletException, SQLException {
        HttpSession session = req.getSession();
        int recordsPerPage = 8;
        int currentPage = 1;
        if (req.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }
        String currentLang = "en";
        if (req.getParameter("currentLang") != null) {
            currentLang = req.getParameter("currentLang");
        }

        Language language = langService.findByName(currentLang);

        String sort = "name";
        if (req.getParameter("sort") != null) {
            sort = req.getParameter("sort");
        }

        List<Product> listOfProducts = productService.findAllLangSortBy(language, (currentPage - 1) * recordsPerPage, recordsPerPage, sort);
        int rows = productService.getNumberOfRows();
        int noOfPages = rows/recordsPerPage;

        if (rows%recordsPerPage > 0) { //noOfPages
            noOfPages++;
        }

        int currentCount = 0;
        if (session.getAttribute("currentCount") != null) {
            currentCount = (int) session.getAttribute("currentCount");
        }
        session.setAttribute("currentCount", currentCount);
        session.setAttribute("noOfPages", noOfPages);
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("recordsPerPage", recordsPerPage);
        session.setAttribute("listOfProducts", listOfProducts);
        session.setAttribute("currentLang", currentLang);
        session.setAttribute("sort", sort);

        return Path.CATALOG;
    }


}