package com.cayena.backend.services;

import com.cayena.backend.dtos.responses.ProductAllResponse;
import com.cayena.backend.dtos.responses.ProductResponse;
import com.cayena.backend.entities.Product;
import com.cayena.backend.repositories.ProductRepository;
import com.cayena.backend.repositories.SupplierRepository;
import com.cayena.backend.services.exceptions.ResourceNotFoundException;
import com.cayena.backend.services.impl.ProductService;
import com.cayena.backend.util.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService service;
    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private SupplierRepository supplierRepositoryMock;

    @BeforeEach
    void setUp() {
        PageImpl<Product> productPages = new PageImpl<>(List.of(ProductCreate.saveProduct()));

        BDDMockito.when(productRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(productPages);
        BDDMockito.when(productRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ProductCreate.saveProduct()));
        BDDMockito.doNothing().when(productRepositoryMock).delete(ArgumentMatchers.any());
        BDDMockito.when(productRepositoryMock.save(ArgumentMatchers.any(Product.class)))
                .thenReturn(ProductCreate.saveProduct());
        BDDMockito.when(supplierRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(SupplierResponseCreate.saveSupplier()));
    }

    @Test
    @DisplayName("save returns product when successful")
    void save_ReturnsProduct_WhenSuccessful() {
        ProductResponse productResponse = service.save(ProductRequestCreate.saveProductPostRequestBody());

        Assertions.assertThat(productResponse).isNotNull().isEqualTo(ProductResponseCreate.saveProduct());
    }

    @Test
    @DisplayName("save returns product throws BadRequestException when supplier is not found")
    void save_ReturnsProduct_ResourceNotFoundException_WhenSupplierIsNotFound(){
        BDDMockito.when(productRepositoryMock.save(ArgumentMatchers.any(Product.class)))
                .thenReturn(ProductCreate.saveProduct());
        BDDMockito.when(supplierRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> service.save(ProductRequestCreate.saveProductPostRequestBody()));
    }


    @Test
    @DisplayName("list returns list of products inside page object when successful")
    void getAllProducts_ReturnsListOfProductsInsidePageObject_WhenSuccessful() {

        PageRequest page = PageRequest.of(0, 8, Sort.unsorted());

        String expectedName = ProductResponseCreate.saveProduct().getName();
        Page<ProductResponse> productPage = service.getAllProducts(page);

        Assertions.assertThat(productPage).isNotNull();
        Assertions.assertThat(productPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns product when successful")
    void findById_ReturnsProduct_WhenSuccessful() {
        String expectedName = ProductResponseCreate.saveProduct().getName();

        ProductAllResponse productAllResponse = service.getProductById(1L);

        Assertions.assertThat(productAllResponse).isNotNull();
        Assertions.assertThat(productAllResponse.getName()).isNotNull().isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns throws ResourceNotFoundException when product is not found")
    void findById_ResourceNotFoundException_WhenProductIsNotFound(){
        BDDMockito.when(productRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> service.getProductById(1L));
    }
    @Test
    @DisplayName("replace updates product when successful")
    void updatesProduct_WhenSuccessful() {
        Assertions.assertThatCode(() -> service.update(1L, ProductRequestCreate.saveProductPostRequestBody()))
                .doesNotThrowAnyException();
        ProductResponse productResponse = service.update(1L, ProductRequestCreate.saveProductPostRequestBody());

        Assertions.assertThat(productResponse).isNotNull();
    }
    @Test
    @DisplayName("update product returns throws ResourceNotFoundException when supplier is not found")
    void updatesProduct_ResourceNotFoundException_WhenSupplierIsNotFound() {
        BDDMockito.when(supplierRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> service.update(1L, ProductRequestCreate.saveProductPostRequestBody()));
    }

    @Test
    @DisplayName("replace increment quantity product when successful")
    void updateQuantityStock_IncrementQuantityProduct_WhenSuccessful() {
        Assertions.assertThatCode(() -> service.updateQuantityStock
                (1L, QuantityProductCreate.incrementQuantityProduct())).doesNotThrowAnyException();
        ProductResponse productResponse = service.updateQuantityStock(1L, QuantityProductCreate.incrementQuantityProduct());
        Assertions.assertThat(productResponse).isNotNull();
    }

    @Test
    @DisplayName("replace decrement above zero quantity product when successful")
    void updateQuantityStock_decrementAboveZeroQuantityProduct_WhenSuccessful() {
        Assertions.assertThatCode(() -> service.updateQuantityStock
                (1L, QuantityProductCreate.decrementAboveZeroQuantityProduct())).doesNotThrowAnyException();
        ProductResponse productResponse = service.updateQuantityStock(1L, QuantityProductCreate.decrementAboveZeroQuantityProduct());
        Assertions.assertThat(productResponse).isNotNull();
    }
    @Test
    @DisplayName(" update quantity stock return EnumExceptionQuantity with invalid enum")
    void updateQuantityStock_EnumExceptionQuantityProduct_WhenSuccessful() {
        Assertions.assertThatExceptionOfType(java.lang.IllegalArgumentException.class)
                .isThrownBy(() -> service.updateQuantityStock(1L, QuantityProductCreate.EnumExceptionQuantityProduct()));
    }

    @Test
    @DisplayName("delete remove product when Successful")
    void delete_RemovesProduct_WhenSuccessful() {
        Assertions.assertThatCode(() -> service.delete(1L))
                .doesNotThrowAnyException();
    }
}
