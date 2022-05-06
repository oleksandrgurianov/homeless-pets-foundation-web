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