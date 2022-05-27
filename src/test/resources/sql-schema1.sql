DROP TABLE IF EXISTS `items`;

create table if not exists 'items' (
    'product_id' int(11) NOT NULL AUTO_INCREMENT,
    'product_name' varchar(10) NOT NULL,
    'price' double NOT NULL,
    primary key('product_id)')
);