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

Drop table if EXISTS `orders`

create table if not exists `orders` (
`order_id` int NOT NULL auto_increment,
`customer_id` int ,
`cost` double,
primary key(`order_id`),
foreign key (`ustomer_id`) REFERENCES `customers`(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

Drop table if not exists `joining`
create table if not exists `joining` (
`order_id` int NOT NULL ,
`product_id` int,
foreign key (`product_id`) REFERENCES  `items`(`product_id`) ON UPDATE CASCADE ON DELETE CASCADE,
foreign key (`order_id`) REFERENCES  `orders`(`order_id`) ON UPDATE CASCADE ON DELETE CASCADE

);