CREATE TABLE product (
  product_id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(255) NULL,
   quantity_in_stock INT NULL,
   unit_price DECIMAL NULL,
   supplier_id BIGINT NULL,
   date_of_creation date NULL,
   date_of_the_last_update date NULL,
   CONSTRAINT pk_product PRIMARY KEY (product_id)
);

ALTER TABLE product ADD CONSTRAINT FK_PRODUCT_ON_SUPPLIER FOREIGN KEY (supplier_id) REFERENCES supplier (suplier_id);