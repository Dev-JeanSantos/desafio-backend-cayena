package com.cayena.backend.services;

import com.cayena.backend.dtos.requesties.ProductRequest;
import com.cayena.backend.dtos.responses.ProductAllResponse;
import com.cayena.backend.dtos.responses.ProductResponse;
import com.cayena.backend.entities.Product;
import com.cayena.backend.repositories.ProductRepository;
import com.cayena.backend.repositories.SupplierRepository;
import com.cayena.backend.services.impl.ProductService;
import com.cayena.backend.util.ProductCreate;
import com.cayena.backend.util.ProductRequestCreate;
import com.cayena.backend.util.ProductResponseCreate;
import com.cayena.backend.util.SupplierResponseCreate;
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

        BDDMockito.when( productRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(productPages);
        BDDMockito.when( productRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ProductCreate.saveProduct()));
        BDDMockito.doNothing().when(productRepositoryMock).delete(ArgumentMatchers.any());
        BDDMockito.when(productRepositoryMock.save(ArgumentMatchers.any(Product.class)))
                .thenReturn(ProductCreate.saveProduct());
        BDDMockito.when(supplierRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(SupplierResponseCreate.saveSupplier()));
    }

    @Test
    @DisplayName("save returns product when successful")
    void save_ReturnsProduct_WhenSuccessful(){
        ProductResponse productResponse = service.save(ProductRequestCreate.saveProductPostRequestBody());

        Assertions.assertThat(productResponse).isNotNull().isEqualTo(ProductResponseCreate.saveProduct());
    }

    @Test
    @DisplayName("list returns list of products inside page object when successful")
    void list_ReturnsListOfProductsInsidePageObject_WhenSuccessful() {

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
    @DisplayName("must return a product with valid id")
    void mustReturnAProductWithValidId(){
        String expectedName = ProductResponseCreate.saveProduct().getName();

        ProductAllResponse productAllResponse = service.getProductById(1L);

        Assertions.assertThat(productAllResponse).isNotNull();
        Assertions.assertThat(productAllResponse.getName()).isNotNull().isEqualTo(expectedName);
    }

    @Test
    @DisplayName("should delete a product successfully passing a valid id")
    void shouldDeleteAProductSuccessfullyPassingAValidId(){
        Assertions.assertThatCode(() ->service.delete(1L))
                .doesNotThrowAnyException();

    }
}
