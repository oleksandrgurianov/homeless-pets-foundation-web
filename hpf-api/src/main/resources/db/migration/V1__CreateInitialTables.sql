CREATE TABLE user
(
    id           int          NOT NULL AUTO_INCREMENT,
    avatar       varchar(255),
    full_name    varchar(255) NOT NULL,
    email        varchar(255) NOT NULL,
    phone_number varchar(255) NOT NULL,
    password     varchar(255) NOT NULL,
    role         varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE administrator
(
    id           int          NOT NULL AUTO_INCREMENT,
    user_id      int          NOT NULL,
    job_position varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES user (id)
        On DELETE CASCADE
);

CREATE TABLE customer
(
    id              int NOT NULL AUTO_INCREMENT,
    user_id         int NOT NULL,
    street          varchar(255),
    postcode        varchar(255),
    city            varchar(255),
    card_number     varchar(255),
    expiration_date varchar(255),
    cvv             varchar(255),
    PRIMARY KEY (id),
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE
);

CREATE TABLE donation
(
    id              int    NOT NULL AUTO_INCREMENT,
    customer_id     int,
    amount          double NOT NULL,
    date_of_receipt date   NOT NULL,
    description     text(65535),
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE pet
(
    id           int          NOT NULL AUTO_INCREMENT,
    customer_id  int,
    icon         varchar(255) NOT NULL,
    type         varchar(255) NOT NULL,
    name         varchar(255) NOT NULL,
    breed        varchar(255) NOT NULL,
    age_category varchar(255),
    gender       varchar(255),
    size         varchar(255),
    color        varchar(255),
    description  text(65535),
    adoption_fee double,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE pet_picture
(
    id      int          NOT NULL AUTO_INCREMENT,
    pet_id  int          NOT NULL,
    picture varchar(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (pet_id) REFERENCES pet (id)
        ON DELETE CASCADE
);