package com.epam.onlinestore;

import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

public final class Path {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    // pages
    public static final String PAGE__LOGIN = "/WEB-INF/jsp/login.jsp";
    public static final String PAGE__LOGUP = "/logup.jsp";
    public static final String PAGE__ERROR_PAGE = "/error_page.jsp";  ///"/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE__LIST_MENU = "/WEB-INF/jsp/client/list_menu.jsp";
    public static final String PAGE__LIST_ORDERS = "/WEB-INF/jsp/admin/list_orders.jsp";
    public static final String PAGE__SETTINGS = "/WEB-INF/jsp/settings.jsp";
    public static final String PAGE__ADMIN = "/WEB-INF/jsp/login.jsp";
    public static final String PAGE__ENTER = "/index.jsp";
    // commands
    //public static final String COMMAND__LIST_ORDERS = "/controller?command=listOrders";
    //public static final String COMMAND__LIST_MENU = "/controller?command=listMenu";

    public static final String CATALOG = "/WEB-INF/view/catalog.jsp";

}