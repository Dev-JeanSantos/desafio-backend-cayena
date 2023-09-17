package com.cayena.backendkotlin.services.impl

import com.cayena.backendkotlin.dtos.requests.ProductRequest
import com.cayena.backendkotlin.dtos.responses.ProductResponse

interface IProductService {
    fun createProduct( productRequest: ProductRequest): ProductResponse?

}
