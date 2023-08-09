package com.cayena.backend.services;

import com.cayena.backend.dtos.requesties.ProductRequest;
import com.cayena.backend.dtos.responses.ProductResponse;

public interface IProductService {
    ProductResponse save(ProductRequest request);
}
