CREATE TABLE administrator
(
    id              int             NOT NULL AUTO_INCREMENT,
    user_id         int             NOT NULL,
    job_position    varchar(255)    NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES user (id)
    On DELETE CASCADE
);