package com.cayena.backendkotlin.dtos.responses

import java.math.BigDecimal

class ProductResponse(
    val name: String,
    val quantityInStock: Int,
    val unitPrice: BigDecimal,
    val supplierName: String
)
