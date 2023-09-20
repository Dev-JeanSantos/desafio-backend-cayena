package com.cayena.backendkotlin.mocks

import com.cayena.backendkotlin.dtos.requests.ProductRequest
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import javax.persistence.criteria.CriteriaBuilder.In

object BuildProductRequest {
    fun buildProductRequest(
        name: String = "Alcatra Bovina",
        @JsonProperty("quantity_in_stock")
        quantityInStock: Int = 150,
        @JsonProperty("unit_price")
        unitPrice: BigDecimal = BigDecimal.valueOf(48.0),
        @JsonProperty("supplier_id")
        supplierId: Long? = 2
    ) = ProductRequest(
        name = name,
        quantityInStock = quantityInStock,
        unitPrice = unitPrice,
        supplierId = supplierId!!
    )
}