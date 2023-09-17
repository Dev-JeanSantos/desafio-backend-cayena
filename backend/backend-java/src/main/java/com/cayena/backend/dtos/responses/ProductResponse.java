package com.cayena.backend.dtos.responses;

import com.cayena.backend.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private Integer quantityInStock;
    private BigDecimal unitPrice;
    private String supplierName;

    public static ProductResponse converter(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getProductId());
        response.setName(product.getName());
        response.setQuantityInStock(product.getQuantityInStock());
        response.setUnitPrice(product.getUnitPrice());
        response.setSupplierName(product.getSupplier().getName());
        return  response;
    }
}
