package com.cayena.backendkotlin.mocks

import com.cayena.backendkotlin.dtos.requests.QuantityProductRequest
import com.cayena.backendkotlin.enums.UpdateType
import com.fasterxml.jackson.annotation.JsonProperty

object BuildQuantityProductRequest {

    fun buildQuantityProductRequestIncrement(

        @JsonProperty("update_type")
        updateType: UpdateType = UpdateType.INCREASE,

        @JsonProperty("quantity_in_stock")
        quantityInStock: Int = 100

    ) = QuantityProductRequest(
        updateType = updateType,
        quantityInStock = quantityInStock,
    )

    fun buildQuantityProductRequestDecrement(

        @JsonProperty("update_type")
        updateType: UpdateType = UpdateType.DECREASE,

        @JsonProperty("quantity_in_stock")
        quantityInStock: Int = 100

    ) = QuantityProductRequest(
        updateType = updateType,
        quantityInStock = quantityInStock,
    )
    fun buildQuantityProductRequestDecrementQuantityInStockAIsLessThanZero(

        @JsonProperty("update_type")
        updateType: UpdateType = UpdateType.DECREASE,

        @JsonProperty("quantity_in_stock")
        quantityInStock: Int = 1000

    ) = QuantityProductRequest(
        updateType = updateType,
        quantityInStock = quantityInStock,
    )
}