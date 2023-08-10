package com.cayena.backend.services;

import com.cayena.backend.dtos.requesties.ProductRequest;
import com.cayena.backend.dtos.requesties.QuantityProductRequest;
import com.cayena.backend.dtos.responses.ProductAllResponse;
import com.cayena.backend.dtos.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProductService {
    ProductResponse save(ProductRequest request);

    Page<ProductResponse> getAllProducts(PageRequest pageRequest);

    ProductAllResponse getProductById(Long id);

    void delete(Long id);

    ProductResponse update(Long id, ProductRequest request);

    ProductResponse updateQuantityStock(Long id, QuantityProductRequest request);
}
