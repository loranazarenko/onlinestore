package com.epam.onlinestore.dao;

/**
 * Holder for fields names of DB tables and beans.
 */
public final class Fields {

    // entities
    public static final String PRODUCT__ID = "id";
    public static final String PRODUCT__NAME = "name";
    public static final String PRODUCT__DESCRIPTION = "description";
    public static final String PRODUCT__PRICE = "price";
    public static final String PRODUCT__CREATE_DATE = "create_date";
    public static final String PRODUCT__COLOR = "color";
    public static final String PRODUCT__IS_DELETED = "is_deleted";
    public static final String PRODUCT__COUNT = "count";
    public static final String PRODUCT__PICTURE = "picture";
    public static final String PRODUCT__CATEGORY_ID = "category_id";

    public static final String ROLE__NAME = "name";
    public static final String ROLE__ID = "id";

    public static final String ACCOUNT__ID = "id";
    public static final String ACCOUNT__LOGIN = "login";
    public static final String ACCOUNT__PASSWORD = "password";
    public static final String ACCOUNT__USER_STATUSES = "status_id";

    public static final String ACCOUNT__ACCOUNT__DETAIL_ID = "account_id";
    public static final String ACCOUNT__DETAIL_ID = "id";
    public static final String ACCOUNT__DETAIL_NAME = "name";
    public static final String ACCOUNT__DETAIL_EMAIL = "email";

    public static final String STATUS_ID = "id";
    public static final String STATUS_NAME= "name";

    /*public static final String ORDER__BILL = "bill";
    public static final String ORDER__USER_ID = "user_id";
    public static final String ORDER__STATUS_ID= "status_id";*/

    public static final String CATEGORY__ID = "id";
    public static final String CATEGORY__NAME = "name";

    public static final String MENU_ITEM__PRICE = "price";
    public static final String MENU_ITEM__NAME = "name";
    public static final String MENU_ITEM__CATEGORY_ID = "category_id";

    // beans
    public static final String RECEIPT_ID = "id";
    public static final String RECEIPT_DESCRIPTION = "description";
    public static final String RECEIPT_TOTAL = "total";
    public static final String RECEIPT_STATUS_ID = "status_id";
    public static final String RECEIPT_ACCOUNT_ID = "account_id";


}
