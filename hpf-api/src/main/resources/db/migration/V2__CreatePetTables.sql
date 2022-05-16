CREATE TABLE pet
(
    id          int             NOT NULL AUTO_INCREMENT,
    customer_id int,
    type        varchar(255)    NOT NULL,
    name        varchar(255)    NOT NULL,
    breed       varchar(255)    NOT NULL,
    ageCategory varchar(255),
    gender      varchar(255),
    size        varchar(255),
    color       varchar(255),
    description text(65535),
    adoptionFee double,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE pet_picture
(
    id      int             NOT NULL AUTO_INCREMENT,
    pet_id  int             NOT NULL,
    picture varchar(255)    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (pet_id) REFERENCES pet (id)
        ON DELETE CASCADE
);