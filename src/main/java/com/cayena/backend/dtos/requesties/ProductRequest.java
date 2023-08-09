package com.cayena.backend.dtos.requesties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class ProductRequest {
    private Long id;
    @NotBlank(message = "Required field")
    @Size(min = 3, max = 30, message = "Field requires 3 to 30 characters")
    private String name;
    @NotNull
    @PositiveOrZero()
    @JsonProperty("quantity_inStock")
    private Integer quantityInStock;
    @JsonProperty("unit_price")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 2, fraction = 2)
    private BigDecimal unitPrice;
    @Min(1)
    @JsonProperty("supplier_id")
    private Long supplierId;
}
