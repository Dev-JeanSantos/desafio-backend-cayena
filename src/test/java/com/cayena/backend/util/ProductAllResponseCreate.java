package com.cayena.backend.util;

import com.cayena.backend.dtos.responses.ProductAllResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductAllResponseCreate {

    public static ProductAllResponse getProductById() {
        return ProductAllResponse.builder()
                .name("Abacaxi")
                .quantityInStock(10)
                .unitPrice(BigDecimal.valueOf(10.0))
                .dateOfCreation(LocalDateTime.now())
                .dateOfTheLastUpdate(LocalDateTime.now())
                .supplier(SupplierResponseCreate.saveSupplier())
                .build();
    }
}
