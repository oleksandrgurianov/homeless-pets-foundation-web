CREATE TABLE user
(
    id          int             NOT NULL AUTO_INCREMENT,
    avatar      varchar(255),
    fullName    varchar(255)    NOT NULL,
    email       varchar(255)    NOT NULL,
    phoneNumber varchar(255)    NOT NULL,
    password    varchar(255)    NOT NULL,
    role        varchar(255)    NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE administrator
(
    id          int             NOT NULL AUTO_INCREMENT,
    user_id     int             NOT NULL,
    jobPosition varchar(255)    NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES user (id)
        On DELETE CASCADE
);

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
    status          boolean         NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE
);