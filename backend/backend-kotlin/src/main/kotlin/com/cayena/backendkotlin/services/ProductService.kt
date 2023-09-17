package com.cayena.backendkotlin.services

import com.cayena.backendkotlin.dtos.requests.ProductRequest
import com.cayena.backendkotlin.dtos.responses.ProductResponse
import com.cayena.backendkotlin.mapper.ProductMapper
import com.cayena.backendkotlin.mapper.ProductRequestMapper
import com.cayena.backendkotlin.repositories.ProductRepository
import com.cayena.backendkotlin.services.impl.IProductService
import org.springframework.stereotype.Service

@Service
class ProductService(
    val productRequestMapper: ProductRequestMapper,
    val productRepository: ProductRepository,
    val productMapper: ProductMapper
) : IProductService {
    override fun createProduct(productRequest: ProductRequest): ProductResponse? {
        val product = productRequestMapper.map(productRequest)
        val newProduct = productRepository.save(product)

        return productMapper.map(newProduct)
    }

}
