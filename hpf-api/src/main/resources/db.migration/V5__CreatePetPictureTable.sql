CREATE TABLE pet_picture
(
    id      int             NOT NULL AUTO_INCREMENT,
    pet_id  int             NOT NULL,
    picture varchar(255)    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (pet_id) REFERENCES pet (id)
);