package com.cayena.backend.dtos.requesties;

import com.cayena.backend.enums.UpdateType;
import com.cayena.backend.services.validations.EnumNamePattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuantityProductRequest {

    @JsonProperty("update_type")
    @EnumNamePattern(anyOf = {UpdateType.DECREASE, UpdateType.INCREASE})
    private UpdateType updateType;

    @NotNull
    @PositiveOrZero()
    @JsonProperty("quantity_inStock")
    private Integer quantityInStock;

}
