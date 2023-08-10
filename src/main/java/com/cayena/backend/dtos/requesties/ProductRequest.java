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
    @NotNull(message = "Required field")
    @PositiveOrZero(message = "Field requires a positive value greater than zero")
    @JsonProperty("quantity_inStock")
    private Integer quantityInStock;
    @JsonProperty("unit_price")
    @DecimalMin(value = "0.0", inclusive = false, message = "Field requires a decimal value in the following format: 0.0")
    @Digits(integer = 4, fraction = 2, message = "Field must be a maximum of 4 characters")
    private BigDecimal unitPrice;
    @Min(1)
    @JsonProperty("supplier_id")
    private Long supplierId;
}
