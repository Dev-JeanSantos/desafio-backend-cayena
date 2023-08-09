package com.cayena.backend.dtos.requesties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private Long id;
    private String name;
    @JsonProperty("quantity_inStock")
    private Integer quantityInStock;
    @JsonProperty("unit_price")
    private BigDecimal unitPrice;
    @JsonProperty("supplier_id")
    private Long supplierId;
}
