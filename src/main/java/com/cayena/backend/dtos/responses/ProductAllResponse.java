package com.cayena.backend.dtos.responses;

import com.cayena.backend.entities.Product;
import com.cayena.backend.entities.Supplier;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductAllResponse {

    @JsonProperty(value = "name",index = 1)
    private String name;
    @JsonProperty(value = "quantity_in_stock", index = 2)
    private Integer quantityInStock;
    @JsonProperty(value = "unit_price", index = 3)
    private BigDecimal unitPrice;
    @JsonProperty(value = "date_of_creation", index = 4)
    private LocalDateTime dateOfCreation;
    @JsonProperty(value = "date_of_the_last_update", index = 5)
    private LocalDateTime dateOfTheLastUpdate;
    private Supplier supplier;

    public static ProductAllResponse converter(Product product) {
        ProductAllResponse response = new ProductAllResponse();
        response.setName(product.getName());
        response.setQuantityInStock(product.getQuantityInStock());
        response.setUnitPrice(product.getUnitPrice());
        response.setDateOfCreation(product.getDateOfCreation());
        response.setDateOfTheLastUpdate(product.getDateOfTheLastUpdate());
        response.setSupplier(product.getSupplier());
        return  response;
    }
}
