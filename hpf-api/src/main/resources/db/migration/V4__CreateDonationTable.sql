CREATE TABLE donation
(
    id              int         NOT NULL AUTO_INCREMENT,
    customer_id     int,
    amount          double      NOT NULL,
    dateOfReceipt   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description     text(65535),
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);