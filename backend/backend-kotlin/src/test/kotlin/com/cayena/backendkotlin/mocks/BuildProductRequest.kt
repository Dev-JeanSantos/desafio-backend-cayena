package com.cayena.backendkotlin.mocks

import com.cayena.backendkotlin.dtos.requests.ProductRequest
import java.math.BigDecimal
import javax.persistence.criteria.CriteriaBuilder.In

object BuildProductRequest {
    fun buildProductRequest(
        name: String = "Alcatra Bovina",
        quantityInStock: Int = 150,
        unitPrice: BigDecimal = BigDecimal.valueOf(48.0),
        idSupplier: Long? = 1
    ) = ProductRequest(
        name = name,
        quantityInStock = quantityInStock,
        unitPrice = unitPrice,
        idSupplier = idSupplier!!
    )
}