package com.cayena.backend.util;

import com.cayena.backend.dtos.responses.ProductResponse;

import java.math.BigDecimal;

public class ProductResponseCreate {

    public static ProductResponse saveProduct() {
        return ProductResponse.builder()
                .id(1L)
                .name("Abacaxi")
                .quantityInStock(10)
                .unitPrice(BigDecimal.valueOf(10.0))
                .supplierName("Mercadao de Madureira")
                .build();
    }
}