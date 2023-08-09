package com.cayena.backend.services.impl;

import com.cayena.backend.config.NotFoundException;
import com.cayena.backend.dtos.requesties.ProductRequest;
import com.cayena.backend.dtos.responses.ProductAllResponse;
import com.cayena.backend.dtos.responses.ProductResponse;
import com.cayena.backend.entities.Product;
import com.cayena.backend.entities.Supplier;
import com.cayena.backend.repositories.ProductRepository;
import com.cayena.backend.repositories.SupplierRepository;
import com.cayena.backend.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Page<ProductResponse> getAllProducts(PageRequest pageRequest) {
        Page<Product> lista = repository.findAll(pageRequest);
        return lista.map(ProductResponse::converter);
    }

    @Override
    public ProductAllResponse getProductById(Long id) {
        Optional<Product> possibleProduct = repository.findById(id);
        Product entity = possibleProduct.orElseThrow(() -> new NotFoundException("Product not found"));
        return ProductAllResponse.converter(entity);
    }
}
