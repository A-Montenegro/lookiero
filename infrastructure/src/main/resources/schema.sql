CREATE TABLE app_user (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(128) NOT NULL UNIQUE,
    age SMALLINT NOT NULL,
    password BINARY(60) NOT NULL,
    birth_date DATE NOT NULL,
    height DECIMAL(3,2) NOT NULL,
    weight SMALLINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE statistic (
    id INT NOT NULL AUTO_INCREMENT,
    birth_year SMALLINT NOT NULL,
    average_bmi DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (id)
);