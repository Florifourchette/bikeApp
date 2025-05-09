CREATE TABLE bike (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       user_id UUID,
                       name VARCHAR(255) NOT NULL,
                       type VARCHAR(255) NOT NULL,
                       brand VARCHAR(255) NOT NULL,
                       model VARCHAR(255) NOT NULL,
                       colour VARCHAR(255) NOT NULL,
                       electric BOOLEAN,
                       purchased_date DATE,
                       number_of_km INTEGER,
                       is_new BOOLEAN NOT NULL,
                       description VARCHAR(255),
                       image_url VARCHAR(255)
);

CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       username VARCHAR(255),
                       email VARCHAR(255),
                       name VARCHAR(255),
                       external_provider_id VARCHAR(255)
);