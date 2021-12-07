package com.epam.onlinestore;

import org.apache.log4j.Logger;

public final class Path {
    private static final Logger logger = Logger.getLogger(Path.class);
    // pages

    public static final String PAGE__SIGNUP = "/signup.jsp";
    public static final String PAGE__ERROR_PAGE = "/error_page.jsp";
    public static final String PAGE__USER_ORDERS = "/user_orders.jsp";
    public static final String PAGE__ADMIN_USER_ORDERS = "/admin_user_orders.jsp";
    public static final String PAGE__ADMIN_USERS = "/admin_users.jsp";
    public static final String PAGE__SETTINGS = "/WEB-INF/jsp/settings.jsp";
    public static final String PAGE__ADMIN = "/admin.jsp";
    public static final String PAGE__USER = "/user.jsp";
    public static final String PAGE__ENTER = "/index.jsp";
    public static final String CATALOG = "/catalog.jsp";
    public static final String PAGE__SINGLE_PRODUCT = "/edit_product.jsp";
    public static final String PAGE__ADD_PRODUCT = "/add_product.jsp";
    public static final String PAGE__DELETE_PRODUCTS = "/delete_products.jsp";
    public static final String PAGE_CART = "/cart.jsp";
    public static final String PAGE_ORDER = "/order.jsp";
    // commands
    //public static final String COMMAND__LIST_ORDERS = "/controller?command=listOrders";
    //public static final String COMMAND__LIST_MENU = "/controller?command=listMenu";



}