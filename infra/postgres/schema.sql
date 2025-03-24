CREATE TABLE product (
                         id VARCHAR(24) NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         price DECIMAL(10, 2),
                         currency VARCHAR(10),
                         value DECIMAL(10, 2),
                         unit VARCHAR(50),
                         kcal INT,
                         protein FLOAT,
                         fat FLOAT,
                         carbohydrate FLOAT,
                         PRIMARY KEY (id),
                         UNIQUE (name)
);


CREATE TABLE meal (
                      id VARCHAR(24) NOT NULL,
                      name VARCHAR(255) NOT NULL,
                      meal_type VARCHAR(50),
                      description TEXT,
                      kcal INT,
                      protein FLOAT,
                      fat FLOAT,
                      carbohydrate FLOAT,
                      price DECIMAL(10, 2),
                      PRIMARY KEY (id)
);


CREATE TABLE meal_product (
                              meal_id VARCHAR(24) NOT NULL,
                              product_id VARCHAR(24) NOT NULL,
                              PRIMARY KEY (meal_id, product_id),
                              FOREIGN KEY (meal_id) REFERENCES meal(id),
                              FOREIGN KEY (product_id) REFERENCES product(id)
);


CREATE TABLE similar_product (
                                 product_id VARCHAR(24) NOT NULL,
                                 similar_product_id VARCHAR(24) NOT NULL,
                                 PRIMARY KEY (product_id, similar_product_id),
                                 FOREIGN KEY (product_id) REFERENCES product(id),
                                 FOREIGN KEY (similar_product_id) REFERENCES product(id)
);