CREATE TABLE supplier (
  suplier_id BIGINT NOT NULL,
   name VARCHAR(255) NULL,
   date_of_creation datetime NULL,
   date_of_the_last_update datetime NULL,
   CONSTRAINT pk_supplier PRIMARY KEY (suplier_id)
);