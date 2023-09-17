package com.cayena.backend.util;

import com.cayena.backend.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductCreate {

    public static Product saveProduct() {
        return Product.builder()
                .productId(1L)
                .name("Abacaxi")
                .quantityInStock(10)
                .unitPrice(BigDecimal.valueOf(10.0))
                .dateOfCreation(LocalDateTime.now())
                .dateOfTheLastUpdate(LocalDateTime.now())
                .supplier(SupplierResponseCreate.saveSupplier())
                .build();
    }
}