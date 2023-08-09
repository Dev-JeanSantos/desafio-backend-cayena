package com.cayena.backend.services.impl;

import com.cayena.backend.dtos.requesties.ProductRequest;
import com.cayena.backend.dtos.responses.ProductResponse;
import com.cayena.backend.entities.Product;
import com.cayena.backend.entities.Supplier;
import com.cayena.backend.repositories.ProductRepository;
import com.cayena.backend.repositories.SupplierRepository;
import com.cayena.backend.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public ProductResponse save(ProductRequest request) {
        Supplier supplier = supplierRepository.getOne(request.getSupplierId());
        Product product = repository.save(Product.converterRequest(request, supplier));
        return ProductResponse.converter(product);
    }
}
