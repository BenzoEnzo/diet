CREATE TABLE product (
                         id bigserial PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         value DECIMAL(10, 2),
                         unit VARCHAR(50),
                         UNIQUE (name)
);


CREATE TABLE meal (
                      id bigserial PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      meal_type VARCHAR(50),
                      description TEXT,
                      kcal INT,
                      protein FLOAT,
                      fat FLOAT,
                      carbohydrate FLOAT,
                      price DECIMAL(10, 2)
);


CREATE TABLE meal_product (
                              meal_id bigserial NOT NULL,
                              product_id bigserial NOT NULL,
                              PRIMARY KEY (meal_id, product_id),
                              FOREIGN KEY (meal_id) REFERENCES meal(id),
                              FOREIGN KEY (product_id) REFERENCES product(id)
);

