CREATE TABLE inventory (
    id BIGINT NOT NULL AUTO_INCREMENT,
    sku_code VARCHAR(255) default null,
    quantity INT(11) default null,
    PRIMARY KEY (id)
);