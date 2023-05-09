INSERT INTO menu_item (name, price) VALUES
    ('Cheeseburger', 10.99),
    ('Chicken Caesar Salad', 8.99),
    ('Spaghetti Bolognese', 12.99),
    ('Fish and Chips', 11.99);

INSERT INTO "order" (client_name) VALUES
    ('John Doe'),
    ('Jane Smith');

INSERT INTO order_content (order_id, menu_item, quantity) VALUES
    (1, 1, 2),
    (1, 2, 1),
    (2, 3, 1),
    (2, 4, 2);

INSERT INTO invoice (order_id, paid) VALUES
    (1, true),
    (2, false);
