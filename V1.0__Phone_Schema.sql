DROP TABLE IF EXISTS order_phones;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS phones;
DROP TABLE IF EXISTS customers;


CREATE TABLE customers (
  customer_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_id varchar(40) NOT NULL,
  first_name varchar(45) NOT NULL, 
  last_name varchar(45) NOT NULL,
  email varchar(45),
  PRIMARY KEY (customer_pk)
);

CREATE TABLE phones (
  phone_pk int unsigned NOT NULL AUTO_INCREMENT,
  phone_id enum('iPhone', 'Galaxy', 'Pixel') NOT NULL,
  trim_level varchar(40) NOT NULL,
  storage int unsigned NOT NULL,
  brand varchar(10) NOT NULL,
  color varchar(20) NOT NULL,
  base_price decimal(9, 2) NOT NULL,
  PRIMARY KEY (phone_pk),
  UNIQUE KEY (phone_id, storage, trim_level)
);

CREATE TABLE orders (
  order_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_fk int unsigned NOT NULL,
  order_type varchar(40) NOT NULL, 
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (order_pk),
  FOREIGN KEY (customer_fk) REFERENCES customers (customer_pk) ON DELETE CASCADE
);

CREATE TABLE order_phones (
  phone_fk int unsigned NOT NULL,
  order_fk int unsigned NOT NULL,
  FOREIGN KEY (phone_fk) REFERENCES phones (phone_pk) ON DELETE CASCADE,
  FOREIGN KEY (order_fk) REFERENCES orders (order_pk) ON DELETE CASCADE
);