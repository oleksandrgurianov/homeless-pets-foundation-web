CREATE TABLE pet
(
    id              int             NOT NULL AUTO_INCREMENT,
    customer_id     int,
    type            varchar(255)    NOT NULL,
    name            varchar(255)    NOT NULL,
    breed           varchar(255)    NOT NULL,
    age_category    varchar(255),
    gender          varchar(255),
    size            varchar(255),
    color           varchar(255),
    description     text(65535),
    adoption_fee    double,
    adopted         boolean         NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);