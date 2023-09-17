package com.cayena.backendkotlin.mapper

import com.cayena.backendkotlin.dominio.Product
import com.cayena.backendkotlin.dtos.requests.ProductRequest
import com.cayena.backendkotlin.exceptions.NotFoundException
import com.cayena.backendkotlin.mapper.Mapper
import com.cayena.backendkotlin.repositories.RepositorySupplier
import org.springframework.stereotype.Component

@Component
class ProductRequestMapper(
    private val repositorySupplier: RepositorySupplier
) : Mapper<ProductRequest, Product> {
    override fun map(t: ProductRequest): Product {
        val supplier = repositorySupplier.findById(t.idSupplier)
            .orElseThrow { NotFoundException("Supplier Not Found")}
        return Product(
            name = t.name,
            unitPrice = t.unitPrice,
            quantityInStock = t.quantityInStock,
            supplier = supplier
        )

    }
}
