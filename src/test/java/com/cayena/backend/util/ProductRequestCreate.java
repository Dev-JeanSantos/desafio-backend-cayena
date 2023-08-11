package com.cayena.backend.util;

import com.cayena.backend.dtos.requesties.ProductRequest;

import java.math.BigDecimal;

public class ProductRequestCreate {

    public static ProductRequest saveProduct() {
        return ProductRequest.builder()
                .id(1L)
                .name("Abacaxi")
                .quantityInStock(10)
                .unitPrice(BigDecimal.valueOf(10.0))
                .supplierId(1L)
                .build();
    }
}