INSERT INTO product (id, name, price, description)
VALUES (10001, 'Test product name', 100, 'Test product description');

INSERT INTO product (id, name, price, description)
VALUES (10002, 'Test product name2', 120, 'Test product description2');

INSERT INTO product_order (id, amount, product_id, creation_date)
VALUES (10001, 1, 10001, '2022-01-01 12:00:00');

INSERT INTO product_order (id, amount, product_id, creation_date)
VALUES (10002, 2, 10001, '2022-01-02 12:00:00');