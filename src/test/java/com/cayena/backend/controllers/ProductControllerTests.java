package com.cayena.backend.controllers;

import com.cayena.backend.dtos.responses.ProductAllResponse;
import com.cayena.backend.dtos.responses.ProductResponse;
import com.cayena.backend.services.impl.ProductService;
import com.cayena.backend.util.ProductAllResponseCreate;
import com.cayena.backend.util.ProductResponseCreate;
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

        BDDMockito.when(productServiceMockk.getProductById(ArgumentMatchers.anyLong()))
                .thenReturn(ProductAllResponseCreate.getProductById());
        BDDMockito.doNothing().when(productServiceMockk).delete(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName("List returns list of products inside page object when success")
    void shouldReturnAPaginatedListOfProducts() {

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
    @DisplayName("must return a product with valid id")
    void mustReturnAProductWithValidId(){
        String expectedName = ProductResponseCreate.saveProduct().getName();

        ProductAllResponse productAllResponse = controller.getProductById(1L).getBody();

        Assertions.assertThat(productAllResponse).isNotNull();
        Assertions.assertThat(productAllResponse.getName()).isNotNull().isEqualTo(expectedName);
    }
    @Test
    @DisplayName("should delete a product successfully passing a valid id")
    void shouldDeleteAProductSuccessfullyPassingAValidId(){

        Assertions.assertThatCode(() ->controller.deleteProduct(1L))
                .doesNotThrowAnyException();

        ResponseEntity<ProductResponse> entity = controller.deleteProduct(1L);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
