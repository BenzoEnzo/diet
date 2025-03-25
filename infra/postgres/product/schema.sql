CREATE TABLE product (
                         id bigserial PRIMARY KEY,
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
                         UNIQUE (name)
);