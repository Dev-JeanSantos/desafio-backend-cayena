package com.cayena.backendkotlin.mapper

import com.cayena.backendkotlin.dominio.Product
import com.cayena.backendkotlin.dtos.requests.ProductRequest
import com.cayena.backendkotlin.services.impl.SupplierService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class ProductRequestMapper constructor(
    private val supplierService: SupplierService,
    private val supplierResponseMapper: SupplierResponseMapper
) : Mapper<ProductRequest, Product> {
    override fun map(t: ProductRequest): Product {
        val supplier = supplierService.getSupplierById(t.supplierId)
        return Product(
            name = t.name,
            unitPrice = t.unitPrice,
            quantityInStock = t.quantityInStock,
            supplier = supplierResponseMapper.map(supplierService.getSupplierById(t.supplierId))
        )
    }
}
