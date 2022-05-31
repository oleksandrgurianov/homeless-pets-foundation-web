CREATE TABLE customer
(
    id                  int             NOT NULL AUTO_INCREMENT,
    user_id             int             NOT NULL,
    street              varchar(255),
    postcode            varchar(255),
    city                varchar(255),
    card_number         varchar(255),
    expiration_date     varchar(255),
    cvv                 varchar(255),
    status              boolean         NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES user (id)
    ON DELETE CASCADE
);