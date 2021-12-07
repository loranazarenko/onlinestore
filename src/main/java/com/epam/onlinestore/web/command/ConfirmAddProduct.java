package com.epam.onlinestore.web.command;

import com.epam.onlinestore.dao.CategoryDAO;
import com.epam.onlinestore.dao.ProductLanguageDAO;
import com.epam.onlinestore.dao.impl.CategoryDAOImpl;
import com.epam.onlinestore.dao.impl.ProductLanguageDAOImpl;
import com.epam.onlinestore.entity.Category;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.exception.ServiceException;
import com.epam.onlinestore.service.LangService;
import com.epam.onlinestore.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ConfirmAddProduct extends Command {
    private static final Logger log = Logger.getLogger(ConfirmAddProduct.class);

    private static final String PRODUCT_NAME = "product-name";
    private static final String PHOTO = "photo";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private static final String COLOR = "color";
    private static final String DESCRIPTION_EN = "descriptionEn";
    private static final String DESCRIPTION_RU = "descriptionRu";
    private static final String ISDELETE = "isDelete";
    private static final String PRODUCT_ID = "productId";
    private static final String COUNT = "count";
    private static final String DATECREATION = "create_date";

    private static final String ERROR = "error";
    private static final String OK = "ok";

    ProductLanguageDAO productLanguageDAO = new ProductLanguageDAOImpl();
    ProductService productService = new ProductService();
    LangService langService = new LangService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse res) throws DaoException, IOException, ServletException, SQLException {
        String message = ERROR;
        request.setCharacterEncoding("cp1251");
        res.setCharacterEncoding("cp1251");
      //  Optional<String> productId = Optional.ofNullable(request.getParameter(PRODUCT_ID));
        Optional<String> productName = Optional.ofNullable(request.getParameter(PRODUCT_NAME));
        Optional<String> photo = Optional.ofNullable(request.getParameter(PHOTO));
        Optional<String> price = Optional.ofNullable(request.getParameter(PRICE));
        Optional<String> count = Optional.ofNullable(request.getParameter(COUNT));
        Optional<String> category = Optional.ofNullable(request.getParameter(CATEGORY));
        Optional<String> descriptionEn = Optional.ofNullable(request.getParameter(DESCRIPTION_EN));
        Optional<String> descriptionRu = Optional.ofNullable(request.getParameter(DESCRIPTION_RU));
        Optional<String> isDelete = Optional.ofNullable(request.getParameter(ISDELETE));
        Optional<String> color = Optional.ofNullable(request.getParameter(COLOR));
        Optional<String> createDate = Optional.ofNullable(request.getParameter(DATECREATION));


        long categoryId = 0;
        Product result = new Product();
      //  productId.isPresent() &&
        if (productName.isPresent() && photo.isPresent() && price.isPresent() && category.isPresent() &&
                descriptionEn.isPresent() && descriptionRu.isPresent() && color.isPresent() && count.isPresent() && createDate.isPresent()) {
            Product product = new Product();
        //    product.setId(Long.parseLong(productId.get()));
            product.setName(productName.get());
            product.setPicture(photo.get());
            product.setPrice(Double.parseDouble(price.get()));
            try {
                categoryId = getCategoryId(category.get());
            } catch (ServiceException e) {
                log.error("Unable to get category id!", e);
            }
            product.setCategoryId(categoryId);
            product.setDescription(descriptionEn.get());
            product.setColor(color.get());
            product.setIs_deleted(isDelete.isPresent());
            product.setCount(Integer.parseInt(count.get()));
            product.setCreate_date(Date.valueOf(createDate.get()));
            result = productService.addNewProduct(product);
            if (result != null) {
                message = OK;
            }

            if(descriptionEn.get()!=null) {
              Language lang = langService.findByName("en");
                productLanguageDAO.setTranslationForProduct(lang, product, descriptionEn.get());
            }
            if(descriptionRu.get()!=null) {
                Language lang = langService.findByName("ru");
                productLanguageDAO.setTranslationForProduct(lang, product, descriptionRu.get());
            }
        }
        request.setAttribute("message", message);

        return ("/controller?command=catalog");
    }

    private long getCategoryId(String categoryName) throws ServiceException {
        long categoryId = -1;
        try {
            CategoryDAO categoryDao = new CategoryDAOImpl();
            List<Category> categoryList = categoryDao.findByName(categoryName);
            if (categoryList.size() > 0) {
                categoryId = categoryList.get(0).getId();
            } else {
                Category category = new Category();
                category.setCategoryName(categoryName);
                categoryId = categoryDao.save(category);
            }
        } catch (DaoException | SQLException e) {
            log.error("Unable to get category id!", e);
        }
        return categoryId;
    }

}
