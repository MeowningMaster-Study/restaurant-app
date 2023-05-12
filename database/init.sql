CREATE TABLE menu_item (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

CREATE TABLE "order" (
    id SERIAL PRIMARY KEY,
    client_name TEXT NOT NULL,
    paid BOOLEAN DEFAULT false
);

CREATE TABLE order_content (
    order_id INT NOT NULL,
    menu_item INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES "order" (id),
    FOREIGN KEY (menu_item) REFERENCES menu_item (id)
);