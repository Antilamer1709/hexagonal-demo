DROP TABLE PUBLIC.product IF EXISTS;
CREATE TABLE PUBLIC.product  (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);