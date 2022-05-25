drop schema testims;

CREATE SCHEMA IF NOT EXISTS `testims`;

Use `testIMS`;

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
    `price` decimal(10,2) DEFAULT NULL,
    primary key (`product_id`)
);

Drop table if EXISTS `orders`;

create table if not exists `orders` (
`order_id` int(10) NOT NULL AUTO_INCREMENT,
`customer_id` int(10) ,
`cost` decimal(10,2),
primary key(`order_id`),
foreign key (`customer_id`) REFERENCES `customers`(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

Drop table if exists `joining`;

create table if not exists `joining` (
`order_id` int NOT NULL ,
`product_id` int,
foreign key (`product_id`) REFERENCES  `items`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE,
foreign key (`order_id`) REFERENCES  `orders`(`order_id`) ON UPDATE CASCADE ON DELETE CASCADE

);