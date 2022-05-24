Use testIMS;

DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `items`;

CREATE TABLE IF NOT EXISTS `items` (
    `product_id` int(11) NOT NULL AUTO_INCREMENT,
    `product_name` varchar(10) DEFAULT NULL,
    `price` double DEFAULT NULL,
    primary key (`product_id`)
);