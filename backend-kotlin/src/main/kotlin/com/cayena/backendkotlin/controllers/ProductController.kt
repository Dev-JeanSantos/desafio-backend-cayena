package com.cayena.backendkotlin.controllers

import com.cayena.backendkotlin.dtos.requests.ProductRequest
import com.cayena.backendkotlin.dtos.responses.ProductResponse
import com.cayena.backendkotlin.services.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/products")
class ProductController(
    val service: ProductService
    ) {

    @PostMapping
    fun createProduct(
        @Valid @RequestBody productRequest: ProductRequest,
        uriComponentsBuilder: UriComponentsBuilder
        ): ResponseEntity<ProductResponse>{

        val productResponse = service.createProduct(productRequest)
        val uri = uriComponentsBuilder.path("id").build().toUri()
        return ResponseEntity.created(uri).body(productResponse)
    }
}