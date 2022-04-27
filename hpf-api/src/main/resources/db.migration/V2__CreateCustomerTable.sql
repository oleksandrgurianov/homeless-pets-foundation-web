CREATE TABLE customer
(
    id              int             NOT NULL AUTO_INCREMENT,
    user_id         int             NOT NULL,
    street          varchar(255),
    postcode        varchar(255),
    city            varchar(255),
    cardNumber      varchar(255),
    expirationDate  varchar(255),
    cvv             varchar(255),
    status          varchar(255),
    PRIMARY KEY (id),
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);