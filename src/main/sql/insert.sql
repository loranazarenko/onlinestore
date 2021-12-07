/*INSERT INTO category (name)
VALUES ('Mobil phones');

INSERT INTO category (name)
VALUES ('EBooks');


insert into status (name)
values ('registered');

  insert into status (name)
values ('paid');

  insert into status (name)
values ('canceled');

    insert into status (name)
values ('in_the_basket');
  */

/*  --------------filling in the table
"Products"---------------------*/
INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('Apple new mac book 2015 March', 899.00,
        'сенсорный', 1,
        'phones/product-1.jpg', 'white', 3, 0);

INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('SmartBuy 612AG Red/Black (SBM-612AG-RK)', 999.00,
        'сенсорный', 1,
        'phones/product-2.jpg', 'black', 2, 0);

INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('Xiaomi Mi True Wireless Earbuds Basic 2 TWSEJ061LS', 564.00,
        'сенсорный', 1,
        'phones/product-3.jpg', 'black', 2, 0);

INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('ASUS TUF Gaming A15 FA506IU-HN200', 894.00,
        'несенсорный', 2,
        'books/product-4.jpg', 'white', 3, 0);

INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('SAMSUNG KKK ', 563.00,
        'несенсорный', 2,
        'books/product-5.jpg', 'white', 4, 0);

INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('NOKIA LA Gaming ', 753.00,
        'несенсорный', 2,
        'books/product-6.jpg', 'black', 5, 0);
/*--------------------*/
INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('Apple iPhone 11 64GB White (MHDC3) Slim Box', 899.00,
        'сенсорный', 1,
        'other/Apple iPhone 11 64GB White (MHDC3) Slim Box.jpg', 'white', 3, 0);

INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('Apple iPhone 11 128GB Black (MHDH3) Slim Box', 999.00,
        'сенсорный', 1,
        'other/Apple iPhone 11 128GB Black (MHDH3) Slim Box.jpg', 'black', 2, 0);

INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('Apple iPhone 13 256GB Midnight (MLQ63)', 564.00,
        'сенсорный', 1,
        'other/Apple iPhone 13 256GB Midnight (MLQ63).jpg', 'black', 2, 0);

INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('Motorola G9 Play 4-64 GB Forest Green (PAKK0009RS)', 894.00,
        'несенсорный', 2,
        'other/Motorola G9 Play 4-64 GB Forest Green (PAKK0009RS).jpg', 'white', 3, 0);

INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('Motorola G60 6-128GB Haze Gray (PANB0007RS)', 563.00,
        'несенсорный', 2,
        'other/Motorola G60 6-128GB Haze Gray (PANB0007RS).jpg', 'white', 4, 0);

INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('Poco M3 4-64GB Blue', 753.00,
        'несенсорный', 2,
        'other/Poco M3 4-64GB Blue.jpg', 'black', 5, 0);

INSERT INTO product (name, price, description, category_id, picture, color, count, is_deleted)
VALUES ('Poco X3 Pro 6-128 Phantom Black', 753.00,
        'сенсорный', 2,
        'other/Poco X3 Pro 6-128 Phantom Black.jpg', 'black', 5, 0);

/*13*/

/*INSERT INTO role (name)
VALUES ('admin');

INSERT INTO role (name)
VALUES ('user');

INSERT INTO role (name) VALUES ('temp')

  INSERT INTO userStatuses(statusName)
VALUES ('active'),
       ('disabled');

 INSERT INTO language (name)
VALUES ('en');

   INSERT INTO language (name)
VALUES ('ru');

   INSERT INTO category_language (name, category_id, language_id)
VALUES ('Mobil phones', 1, 1);

    INSERT INTO category_language (name, category_id, language_id)
VALUES ('Мобильные телефоны', 1, 2);

INSERT INTO category_language (name, category_id, language_id)
VALUES ('EBooks', 2, 1);

INSERT INTO category_language (name, category_id, language_id)
VALUES ('Электронные книги', 2, 2);


     INSERT INTO product_language (description, product_id, language_id)
VALUES ('sensory', 1, 1);

    INSERT INTO product_language (description, product_id, language_id)
VALUES ('сенсорный', 1, 2);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('sensory, 2, 1);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('сенсорный', 2, 2);

   INSERT INTO product_language (description, product_id, language_id)
VALUES ('sensory', 3, 1);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('сенсорный', 3, 2);


  INSERT INTO product_language (description, product_id, language_id)
VALUES ('non-sensory', 4, 1);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('несенсорный', 4, 2);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('non-sensory', 5, 1);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('несенсорный', 5, 2);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('non-sensory', 6, 1);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('несенсорный', 6, 2);

    INSERT INTO product_language (description, product_id, language_id)
VALUES ('sensory', 7, 1);

    INSERT INTO product_language (description, product_id, language_id)
VALUES ('сенсорный', 7, 2);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('sensory, 8, 1);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('сенсорный', 8, 2);

   INSERT INTO product_language (description, product_id, language_id)
VALUES ('sensory', 9, 1);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('сенсорный', 9, 2);

INSERT INTO product_language (description, product_id, language_id)
VALUES ('non-sensory', 10, 1);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('несенсорный', 10, 2);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('non-sensory', 11, 1);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('несенсорный', 11, 2);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('non-sensory', 12, 1);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('несенсорный', 12, 2);

 INSERT INTO product_language (description, product_id, language_id)
VALUES ('sensory', 13, 1);

  INSERT INTO product_language (description, product_id, language_id)
VALUES ('сенсорный', 13, 2);

  */