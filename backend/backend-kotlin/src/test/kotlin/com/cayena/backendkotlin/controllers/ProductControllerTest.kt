package com.cayena.backendkotlin.controllers

import com.cayena.backendkotlin.dtos.requests.ProductRequest
import com.cayena.backendkotlin.mocks.BuildProductRequest
import com.cayena.backendkotlin.repositories.ProductRepository
import com.cayena.backendkotlin.services.impl.ProductService
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
class ProductControllerTest{

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var repository: ProductRepository

    companion object{
        const val URL: String = "/api/v1/products"
    }

    @BeforeEach
    fun Setup(){
        repository.deleteAll()
    }


    @AfterEach
    fun tearDown() = repository.deleteAll()


    @Test
    @DisplayName("save returns product when successful")
    fun `save_ReturnsProduct_WhenSuccessful`(){

        val request: ProductRequest = BuildProductRequest.buildProductRequest()
        val requestConverterString = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestConverterString)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alcatra Bovina"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.quantityInStock").value("150"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value("48.0"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.supplierName").value("GUANABARA"))
            .andDo(MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("save returns NotFoundException when supplier id not exists")
    fun `save_ReturnsNotFoundException_WhenSupplierIdNotExists`(){

        val request: ProductRequest = BuildProductRequest.buildProductRequest(idSupplier = 100)
        val requestConverterString = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestConverterString)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Supplier Not Found"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("NOT_FOUND"))
            .andDo(MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("save returns NotFoundException when prodcut name with less than 3 caracteres")
    fun `save_ReturnsNotFoundException_WhenProdcutNameWithLessThan3Caracteres`(){

        val request: ProductRequest = BuildProductRequest.buildProductRequest(name = "Ma")
        val requestConverterString = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestConverterString)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("{name=Field requires 3 to 30 characters}"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("BAD_REQUEST"))
            .andDo(MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("save returns MethodArgumentNotValidException when product name empty")
    fun `save_ReturnsMethodArgumentNotValidException_WhenProductNameEmpty`(){

        val request: ProductRequest = BuildProductRequest.buildProductRequest(name = " ")
        val requestConverterString = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestConverterString)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("BAD_REQUEST"))
            .andDo(MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("getProductById returns product by sucessfull when search with an id of an existing product in the database")
    fun `getProductById_ReturnsProductsBySucessfull_WhenSearchWithAnIdofAnExistingProductInTheDatabase`(){

        val product = repository.save(BuildProductRequest.buildProductRequest().toEntity())

        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/${product.productId}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alcatra Bovina"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.quantityInStock").value(150))
            .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value(48.0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.supplierName").value("GUANABARA"))
            .andDo(MockMvcResultHandlers.print()
            )
    }
    @Test
    @DisplayName("getProductById returns NotFoundException when not existing product in the database")
    fun `getProductById_ReturnsNotFoundException_WhenNotExistingProductInTheDatabase`(){

        val idNotFound: Long = 2L
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/$idNotFound")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Product Not Found"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("NOT_FOUND"))
            .andDo(MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("getProductById returns MethodArgumentNotValidException when search with an invalid product id")
    fun `getProductById_ReturnsMethodArgumentNotValidException_WhenSearchWithAnInvalidProductId`(){

        val idInvalid: String = "A"
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/$idInvalid")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("BAD_REQUEST"))
            .andDo(MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("getAllProducts returns all products pagination when search without any parameters")
    fun `getAllProducts_ReturnsAllProductsPagination_WhenSearchWithoutAnyParameters`(){
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("BAD_REQUEST"))
            .andDo(MockMvcResultHandlers.print()
            )
    }
}