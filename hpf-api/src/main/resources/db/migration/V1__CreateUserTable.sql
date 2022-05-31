CREATE TABLE user
(
    id              int             NOT NULL AUTO_INCREMENT,
    avatar          varchar(255),
    full_name       varchar(255)    NOT NULL,
    email           varchar(255)    NOT NULL,
    phone_number    varchar(255)    NOT NULL,
    password        varchar(255)    NOT NULL,
    role            varchar(255)    NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);