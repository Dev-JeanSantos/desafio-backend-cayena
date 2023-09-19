package com.cayena.backendkotlin.controllers

import com.cayena.backendkotlin.dtos.requests.ProductRequest
import com.cayena.backendkotlin.dtos.requests.QuantityProductRequest
import com.cayena.backendkotlin.dtos.responses.ProductResponse
import com.cayena.backendkotlin.services.impl.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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
    ): ResponseEntity<ProductResponse> {

        val productResponse = service.createProduct(productRequest)
        val uri = uriComponentsBuilder.path("id").build().toUri()
        return ResponseEntity.created(uri).body(productResponse)
    }

    @GetMapping("/{id}")
    fun getProductById(
        @PathVariable id: Long
    ): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok().body(service.getProductById(id))
    }

    @GetMapping
    fun getAllProducts(
        @RequestParam(required = false) nameProduct: String?,
        @PageableDefault(size = 10, sort = ["productId"], direction = Sort.Direction.DESC) pageable: Pageable
    ): ResponseEntity<Page<ProductResponse>> {
        val products: Page<ProductResponse> = service.getAllProducts(nameProduct, pageable)
        return ResponseEntity.ok().body(products)
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @Valid @RequestBody productRequest: ProductRequest
    ): ResponseEntity<ProductResponse> {
        val product: ProductResponse = service.updateProduct(id, productRequest)
        return ResponseEntity.ok().body(product)
    }

    @PatchMapping("/{id}")
    fun updateQuantityStockProduct(
        @PathVariable id: Long,
        @RequestBody @Valid quantityProductRequest: QuantityProductRequest
        ): ResponseEntity<ProductResponse>{
        val product: ProductResponse = service.updateQuantityStock(id, quantityProductRequest)
        return ResponseEntity.ok().body(product)
    }
}