CREATE TABLE supplier (
  supplier_id BIGINT NOT NULL,
   name_supplier VARCHAR(255) NULL,
   date_of_creation datetime NULL,
   date_of_the_last_update datetime NULL,
   CONSTRAINT pk_supplier PRIMARY KEY (supplier_id)
);