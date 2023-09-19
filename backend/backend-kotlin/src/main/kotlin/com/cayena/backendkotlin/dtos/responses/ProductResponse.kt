package com.cayena.backendkotlin.dtos.responses

import com.cayena.backendkotlin.dominio.Product
import com.cayena.backendkotlin.dominio.Supplier
import java.math.BigDecimal
import java.time.LocalDateTime

class ProductResponse(
    val name: String,
    val quantityInStock: Int,
    val unitPrice: BigDecimal,
    val supplierName: String
){
    fun toEntity(): Product = Product(
        name = this.name,
        quantityInStock = this.quantityInStock,
        unitPrice = this.unitPrice,
        supplier = Supplier(
            supplierId = 1,
            nameSupplier = supplierName,
            dateOfCreation = LocalDateTime.now(),
            dateOfTheLastUpdate = LocalDateTime.now()
        )
    )
}

