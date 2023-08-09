package com.cayena.backend.controllers;

import com.cayena.backend.dtos.requesties.ProductRequest;
import com.cayena.backend.dtos.responses.ProductResponse;
import com.cayena.backend.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/api/v1/products")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest request) {
        System.out.println(request);
        ProductResponse response = service.save(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(request.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
