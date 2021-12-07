package com.epam.onlinestore.dao.connection;


public abstract class DbConst {

    public static final String SAVE_CATEGORY = "INSERT INTO category (name) VALUES (?)";
    public static final String FIND_CATEGORY_BY_NAME = "SELECT * FROM category WHERE name=?";
    public static final String FIND_CATEGORY_BY_ID = "SELECT * FROM category WHERE id=?";
    public static final String FIND_ALL_CATEGORY = "SELECT * FROM category";

    public static final String SQLLISTCATEGORYBYLANG = "SELECT category.id, category_language.name FROM category" +
            " JOIN category_language ON category_language.category_id = category.id" +
            " WHERE LANGUAGE_ID=?;";


    public static final String INSERT_PRODUCT_LANG_DESCRIPTION = "INSERT INTO product_language(description,product_id,language_id) VALUES (?,?,?)";
    public static final String GET_DESCRIPTION_BY_ID = "select description from product_language where product_id=? and language_id=?;";
    public static final String UPDATE_PRODUCT_LANG_DESCRIPTION = "UPDATE product_language" +
            " SET " +
            " description=?" +
            " WHERE product_id=? and language_id=?;";

    public static final String GET_LANG_BY_NAME = "select * from language where name=?";

    public static final String SQL_LIST_PRODUCTS = "SELECT * FROM product WHERE is_deleted=?";

    public static final String FIND_PRODUCTS_BY_CATEGORY_ID_QUERY = "SELECT * FROM product WHERE category_id=?";
    public static final String FIND_PRODUCT_BY_NAME_QUERY = "SELECT * FROM product WHERE name=?";
    public static final String SAVE_PRODUCT_QUERY = "INSERT INTO product " +
            " (name, description, price, create_date, color, picture, count, category_id, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_PRODUCT_QUERY = "UPDATE product SET name=?, " +
            "description=?, price=?, create_date=?, color=?, " +
            "picture=?, count=?, category_id=?, is_deleted=? WHERE id=?";
    public static final String FIND_PRODUCTS_BY_ID_QUERY = "SELECT * FROM product WHERE id=?";


    public static final String SQL_LIST_PRODUCT_BY_LANG = "SELECT product.id,product.name, product.price, product.create_date, product.color, product.picture, product.count," +
            " product.category_id, product.is_deleted, product_language.description FROM product" +
            " JOIN product_language ON product_language.product_id = product.id" +
            " WHERE LANGUAGE_ID=? LIMIT ?, ?;";

    public static final String FIND_COUNT_BY_ID = "SELECT COUNT FROM product WHERE id=?";


    public static final String INSERT_ACCOUNT = "insert into account (login, password, role_id) values (?,?,?)";
    public static final String GET_ALL_USERS = "SELECT * FROM account ORDER BY id;";
    public static final String FIND_USER_BY_ID = "select * from account where id=?";
    public static final String FIND_USER_BY_LOGIN = "select * from account where login=?";
    public static final String UPDATE_ACCOUNT = "UPDATE account" +
            " SET " +
            " login=?," +
            " password=?," +
            " role_id=?," +
            " status_id=?" +
            " WHERE id=?";

    public static final String UPDATE_ACCOUNT_AC_DETAIL_ID = "UPDATE account" +
            " SET " +
            " account_detail_id=?" +
            " WHERE id=?";


    public static final String GET_USER_BY_USERNAME = "SELECT id, name, email, account_id FROM account_detail INNER JOIN users u on account_detail.accountId = u.id WHERE login=?";
    public static final String GET_USER_BY_ID = "SELECT * FROM account_detail WHERE id=?";
    public static final String INSERT_USER_INFO = "INSERT INTO account_detail(name,email,account_id) VALUES (?,?,?)";
    public static final String UPDATE_USER_INFO = "UPDATE account_detail SET account_id =? WHERE id=?";


    public static final String ADD_RECEIPT = "INSERT INTO receipt (description, total, status_id, " +
            "account_id) VALUES (?, ?, ?, ?)";

    public static final String FIND_RECEIPTS_BY_ACCOUNT_ID = "SELECT * FROM  receipt  WHERE account_id=?;";

    public static final String FIND_ALL_RECEIPTS = "SELECT * FROM  receipt;";

    public static final String DELETE_RECEIPT = "DELETE FROM receipt WHERE id=?;";

    public static final String UPDATE_RECEIPT= "UPDATE receipt SET " +
            "description=?, total=?, create_date=?, status_id=?, " +
            "account_id=? WHERE id=?";

    public static final String UPDATE_STATUS_RECEIPT= "UPDATE receipt SET " +
            "status_id=? WHERE id=?";

    public static final String GET_ROLE_BY_ID = "select name from role where id=?";
    public static final String GET_ROLE_BY_NAME = "select * from role where name=?";

    public static final String BLOCK_USER = "UPDATE account SET status=2 where id = ?";
    public static final String UNBLOCK_USER = "UPDATE account SET status=1 where id = ?";


    private DbConst() {
    }

}
