package com.cayena.backend.controllers;

import com.cayena.backend.dtos.requesties.ProductRequest;
import com.cayena.backend.dtos.requesties.QuantityProductRequest;
import com.cayena.backend.dtos.responses.ProductAllResponse;
import com.cayena.backend.dtos.responses.ProductResponse;
import com.cayena.backend.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductRequest request) {
        ProductResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(

            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy );
        Page<ProductResponse> list = service.getAllProducts(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductAllResponse> getProductById(@PathVariable Long id){
        ProductAllResponse response = service.getProductById(id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest request){
        ProductResponse response = service.update(id, request);
        return ResponseEntity.ok().body(response);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> increasOrDecreaseQuantityStock(@PathVariable Long id, @RequestBody @Valid QuantityProductRequest request){
        ProductResponse response = service.updateQuantityStock(id, request);
        return ResponseEntity.ok().body(response);
    }
}
