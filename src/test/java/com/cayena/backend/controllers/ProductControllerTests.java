package com.cayena.backend.controllers;

import com.cayena.backend.dtos.requesties.ProductRequest;
import com.cayena.backend.dtos.requesties.QuantityProductRequest;
import com.cayena.backend.dtos.responses.ProductAllResponse;
import com.cayena.backend.dtos.responses.ProductResponse;
import com.cayena.backend.services.impl.ProductService;
import com.cayena.backend.util.ProductAllResponseCreate;
import com.cayena.backend.util.ProductRequestCreate;
import com.cayena.backend.util.ProductResponseCreate;
import com.cayena.backend.util.QuantityProductCreate;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(SpringExtension.class)
public class ProductControllerTests {

    @InjectMocks
    private ProductController controller;
    @Mock
    private ProductService productServiceMockk;

    @BeforeEach
    void setUp() {
        PageImpl<ProductResponse> productResponsesPage = new PageImpl<>(List.of(ProductResponseCreate.saveProduct()));

        BDDMockito.when(productServiceMockk.getAllProducts(ArgumentMatchers.any()))
                .thenReturn(productResponsesPage);
        BDDMockito.when(productServiceMockk.getProductById(anyLong()))
                .thenReturn(ProductAllResponseCreate.getProductById());
        BDDMockito.doNothing().when(productServiceMockk).delete(anyLong());
        BDDMockito.when(productServiceMockk.save(ArgumentMatchers.any(ProductRequest.class)))
                .thenReturn(ProductResponseCreate.saveProduct());
        BDDMockito.when(productServiceMockk.update(anyLong(),any(ProductRequest.class)))
                .thenReturn(ProductResponseCreate.saveProduct());
        BDDMockito.when(productServiceMockk.updateQuantityStock(anyLong(),any(QuantityProductRequest.class)))
                .thenReturn(ProductResponseCreate.saveProduct());

    }


    @Test
    @DisplayName("save returns product when successful")
    void save_ReturnsProduct_WhenSuccessful(){

        ProductResponse productResponse = controller.saveProduct
                (ProductRequestCreate.saveProductPostRequestBody()).getBody();

        Assertions.assertThat(productResponse).isNotNull().isEqualTo(ProductResponseCreate.saveProduct());

    }

    @Test
    @DisplayName("list returns list of products inside page object when successful")
    void list_ReturnsListOfProductsInsidePageObject_WhenSuccessful() {
        Page <ProductResponse> page = new PageImpl(List.of(ProductResponseCreate.saveProduct()));

        String expectedName = ProductResponseCreate.saveProduct().getName();
        Page<ProductResponse> productPage = controller.getAllProducts(1,1,"ASC", "name").getBody();

        Assertions.assertThat(productPage).isNotNull();
        Assertions.assertThat(productPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(productPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns product when successful")
    void findById_ReturnsProduct_WhenSuccessful(){
        String expectedName = ProductResponseCreate.saveProduct().getName();

        ProductAllResponse productAllResponse = controller.getProductById(1L).getBody();

        Assertions.assertThat(productAllResponse).isNotNull();
        Assertions.assertThat(productAllResponse.getName()).isNotNull().isEqualTo(expectedName);
    }

    @Test
    @DisplayName("replace updates product when successful")
    void replace_UpdatesProduct_WhenSuccessful(){

        Assertions.assertThatCode(() ->controller.updateProduct(1L, ProductRequestCreate.saveProductPostRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<ProductResponse> productResponse = controller.updateProduct(1L, ProductRequestCreate.saveProductPostRequestBody());

        Assertions.assertThat(productResponse).isNotNull();
        Assertions.assertThat(productResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("replace increment quantity product when successful")
    void replace_IncrementQuantityProduct_WhenSuccessful(){

        Assertions.assertThatCode(() -> controller.increasOrDecreaseQuantityStock
                (1L, QuantityProductCreate.incrementQuantityProduct()))
                .doesNotThrowAnyException();

        ResponseEntity<ProductResponse> productResponse = controller.increasOrDecreaseQuantityStock
                (1L,QuantityProductCreate.incrementQuantityProduct());

        Assertions.assertThat(productResponse).isNotNull();
        Assertions.assertThat(productResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    @DisplayName("delete removes product when successful")
    void delete_RemovesProduct_WhenSuccessful(){

        Assertions.assertThatCode(() ->controller.deleteProduct(1L))
                .doesNotThrowAnyException();

        ResponseEntity<ProductResponse> entity = controller.deleteProduct(1L);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
