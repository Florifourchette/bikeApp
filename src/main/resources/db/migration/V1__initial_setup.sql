CREATE TABLE bike (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       name VARCHAR(255) NOT NULL,
                       brand VARCHAR(255) NOT NULL,
                       model VARCHAR(255) NOT NULL,
                       colour VARCHAR(255) NOT NULL,
                       purchased_date DATE,
                       number_of_km INTEGER,
                       is_new BOOLEAN NOT NULL,
                       description VARCHAR(255)
);

CREATE TABLE users (
                       id VARCHAR(255) PRIMARY KEY DEFAULT gen_random_uuid(),
                       name VARCHAR(255) NOT NULL
);