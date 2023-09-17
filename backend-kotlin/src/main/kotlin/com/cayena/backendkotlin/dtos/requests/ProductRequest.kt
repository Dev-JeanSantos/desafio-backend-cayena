package com.cayena.backendkotlin.dtos.requests

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import javax.validation.constraints.*

data class ProductRequest(

    @field:Size(min = 3, max = 30, message = "Field requires 3 to 30 characters")
    @field:NotBlank(message = "Field requires only alphabetic characters")
    val name: String,

    @NotEmpty
    @PositiveOrZero
    @NotNull
    @JsonProperty("quantity_in_stock")
    val quantityInStock: Int,

    @Digits(integer = 2, fraction = 1)
    @JsonProperty("unit_price")
    val unitPrice: BigDecimal,

    @Min(1)
    @JsonProperty("id_supplier")
    val idSupplier: Long
)
