CREATE TABLE donation
(
    id              int         NOT NULL AUTO_INCREMENT,
    customer_id     int,
    amount          double      NOT NULL,
    dateOfReceipt   timestamp,
    description     varchar(255),
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);