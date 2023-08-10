package com.cayena.backend.dtos.requesties;

import com.cayena.backend.enums.UpdateType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class QuantityProductRequest {

    @JsonProperty("update_type")
    private UpdateType updateType;

    @NotNull
    @PositiveOrZero()
    @JsonProperty("quantity_inStock")
    private Integer quantityInStock;

}
