package com.cayena.backend.util;

import com.cayena.backend.dtos.requesties.ProductRequest;

import java.math.BigDecimal;

public class ProductRequestCreate {

    public static ProductRequest saveProductPostRequestBody() {
        return ProductRequest.builder()
                .id(ProductResponseCreate.saveProduct().getId())
                .name(ProductResponseCreate.saveProduct().getName())
                .quantityInStock(ProductResponseCreate.saveProduct().getQuantityInStock())
                .unitPrice(ProductResponseCreate.saveProduct().getUnitPrice())
                .supplierId(1L)
                .build();
    }
}