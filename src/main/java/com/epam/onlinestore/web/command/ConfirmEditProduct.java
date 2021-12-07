package com.epam.onlinestore.web.command;

import com.epam.onlinestore.dao.ProductLanguageDAO;
import com.epam.onlinestore.dao.impl.ProductLanguageDAOImpl;
import com.epam.onlinestore.entity.Language;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.ConnectionException;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.service.LangService;
import com.epam.onlinestore.service.ProductService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

public class ConfirmEditProduct extends Command {
    private static final Logger log = LogManager.getLogger(CommandContainer.class);
    ProductLanguageDAO productLanguageDAO = new ProductLanguageDAOImpl();
    LangService langService = new LangService();

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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoException, ConnectionException {
        request.setCharacterEncoding("cp1251");
        response.setCharacterEncoding("cp1251");

        Optional<String> productId = Optional.ofNullable(request.getParameter(PRODUCT_ID));
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

        ProductService productService = new ProductService();

        if (productId.isPresent() && productName.isPresent() && photo.isPresent() && price.isPresent() && category.isPresent() &&
                descriptionEn.isPresent() && descriptionRu.isPresent() && color.isPresent() && count.isPresent() && createDate.isPresent()) {
            Product product = new Product();
            product.setId(Long.parseLong(productId.get()));
            product.setName(productName.get());
            product.setPicture(photo.get());
            product.setPrice(Double.parseDouble(price.get()));
            long categoryId = Long.parseLong(category.get());
            product.setCategoryId(categoryId);
            product.setDescription(descriptionEn.get());
            product.setColor(color.get());
            boolean isDel = isDelete.isPresent() && !isDelete.get().equals("");
            product.setIs_deleted(isDel);
            product.setCount(Integer.parseInt(count.get()));
            product.setCreate_date(Date.valueOf(createDate.get()));
            productService.updateProduct(product);

            if (descriptionEn.get() != null) {
                Language lang = langService.findByName("en");
                productLanguageDAO.updateTranslationForProduct(lang, product, descriptionEn.get());
            }
            if (descriptionRu.get() != null) {
                Language lang = langService.findByName("ru");
                productLanguageDAO.updateTranslationForProduct(lang, product, descriptionRu.get());
            }
        }
        return ("/controller?command=catalog");

    }


}
