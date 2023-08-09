package com.cayena.backend.dtos.responses;

import com.cayena.backend.entities.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private String name;
    private Integer quantityInStock;
    private BigDecimal unitPrice;
    private String supplierName;

    public static ProductResponse converter(Product product) {
        ProductResponse response = new ProductResponse();
        response.setName(product.getName());
        response.setQuantityInStock(product.getQuantityInStock());
        response.setUnitPrice(product.getUnitPrice());
        response.setSupplierName(product.getSupplierId().getName());
        return  response;
    }
}
