package com.cayena.backendkotlin.mapper

import com.cayena.backendkotlin.dominio.Product
import com.cayena.backendkotlin.dtos.responses.ProductResponse
import org.springframework.stereotype.Component

@Component
class ProductMapper : Mapper<Product, ProductResponse> {
    override fun map(t: Product): ProductResponse {
        return ProductResponse(
            name = t.name,
            quantityInStock = t.quantityInStock,
            unitPrice = t.unitPrice,
            supplierName = t.supplier.nameSupplier
        )
    }
}
