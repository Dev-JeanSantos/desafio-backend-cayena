package com.cayena.backend.entities;

import com.cayena.backend.dtos.requesties.ProductRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("product_id")
    private Long productId;
    private String name;
    private Integer quantityInStock;
    private BigDecimal unitPrice;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplierId;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfTheLastUpdate;

    public static Product converterRequest(ProductRequest request, Supplier supplier) {
        Product product = new Product();
        product.setProductId(request.getId());
        product.setName(request.getName());
        product.setQuantityInStock(request.getQuantityInStock());
        product.setUnitPrice(request.getUnitPrice());
        product.setSupplierId(supplier);

        return product;
    }
}
