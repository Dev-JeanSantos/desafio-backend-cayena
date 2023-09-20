package com.cayena.backendkotlin.controllers

import com.cayena.backendkotlin.dominio.Product
import com.cayena.backendkotlin.dtos.requests.ProductRequest
import com.cayena.backendkotlin.dtos.requests.QuantityProductRequest
import com.cayena.backendkotlin.mocks.BuildProductRequest
import com.cayena.backendkotlin.mocks.BuildQuantityProductRequest
import com.cayena.backendkotlin.repositories.ProductRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration
class ProductControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var repository: ProductRepository

    lateinit var product: Product
    companion object {
        const val URL: String = "/api/v1/products"
    }

    @BeforeEach
    fun Setup() {
        repository.deleteAll()
        product = repository.save(BuildProductRequest.buildProductRequest().toEntity())
    }
    @AfterEach
    fun tearDown() = repository.deleteAll()

    @Test
    @DisplayName("save returns product when successful")
    fun `save_ReturnsProduct_WhenSuccessful`() {

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
            .andExpect(MockMvcResultMatchers.jsonPath("$.supplierName").value("CASA DAS CARNES CARIOCA"))
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("save returns NotFoundException when supplier id not exists")
    fun `save_ReturnsNotFoundException_WhenSupplierIdNotExists`() {

        val request: ProductRequest = BuildProductRequest.buildProductRequest(supplierId = 100)
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
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("save returns NotFoundException when prodcut name with less than 3 caracteres")
    fun `save_ReturnsNotFoundException_WhenProdcutNameWithLessThan3Caracteres`() {

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
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("save returns MethodArgumentNotValidException when product name empty")
    fun `save_ReturnsMethodArgumentNotValidException_WhenProductNameEmpty`() {

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
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("getProductById returns product by sucessfull when search with an id of an existing product in the database")
    fun `getProductById_ReturnsProductsBySucessfull_WhenSearchWithAnIdofAnExistingProductInTheDatabase`() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/${product.productId}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alcatra Bovina"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.quantityInStock").value(150))
            .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value(48.0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.supplierName").value("CASA DAS CARNES CARIOCA"))
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("getProductById returns NotFoundException when not existing product in the database")
    fun `getProductById_ReturnsNotFoundException_WhenNotExistingProductInTheDatabase`() {

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
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("getProductById returns MethodArgumentNotValidException when search with an invalid product id")
    fun `getProductById_ReturnsMethodArgumentNotValidException_WhenSearchWithAnInvalidProductId`() {

        val idInvalid: String = "A"
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/$idInvalid")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("BAD_REQUEST"))
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("getAllProducts returns all products pagination when search without any parameters")
    fun `getAllProducts_ReturnsAllProductsPagination_WhenSearchWithoutAnyParameters`() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(11))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].name").value("Alcatra Bovina"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].quantityInStock").value(150))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].unitPrice").value(48.0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].supplierName").value("CASA DAS CARNES CARIOCA"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DisplayName("deleteProduct returns no content when pass an existing productId in the databases")
    fun `deleteProduct_ReturnsNoContent_WhenPassAnExistingIdProductInThDatabases`() {

        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/${product.productId}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
            .andDo(MockMvcResultHandlers.print())
        val possibleProdduct = repository.findById(product.productId!!)
        assertFalse(possibleProdduct.isPresent)
    }

    @Test
    @DisplayName("deleteProduct returns NotFoundException when not existing product in the database")
    fun `deleteProduct_ReturnsNotFoundException_WhenNotExistingProductInTheDatabase`() {

        val idNotFound: Long = 2L
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/$idNotFound")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Product Not Found"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("NOT_FOUND"))
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("updateProduct returns product update when existing product in the database")
    fun `updateProduct_ReturnsProdcutUpdate_WhenExistingProductInTheDatabase`() {

        val productUpdate: ProductRequest = BuildProductRequest.buildProductRequest(
            name = "Contra-filé",
            quantityInStock = 500,
            unitPrice = BigDecimal.valueOf(25.0)
        )

        val json = objectMapper.writeValueAsString(productUpdate)

        mockMvc.perform(
            MockMvcRequestBuilders.put("$URL/${product.productId}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Contra-filé"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.quantityInStock").value(500))
            .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value(25.0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.supplierName").value("CASA DAS CARNES CARIOCA"))
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("updateProduct returns NotFoundException when not existing supplier in the database")
    fun `updateProduct_ReturnsNotFoundException_WhenNotExistingSupplierInTheDatabase`() {

        val productUpdate: ProductRequest = BuildProductRequest.buildProductRequest(
            name = "Contra-filé",
            quantityInStock = 500,
            unitPrice = BigDecimal.valueOf(25.0),
            supplierId = 100
        )

        val json = objectMapper.writeValueAsString(productUpdate)

        mockMvc.perform(
            MockMvcRequestBuilders.put("$URL/${product.productId}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Supplier Not Found"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("NOT_FOUND"))
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("updateQuantityStockProduct returns product update in incremented when existing product in the database")
    fun `updateQuantityStockProduct_ReturnsProdcutUpdateInIncremeneted_WhenExistingProductInTheDatabase`() {

        val productQuantityUpdate: QuantityProductRequest =
            BuildQuantityProductRequest.buildQuantityProductRequestIncrement()

        val json = objectMapper.writeValueAsString(productQuantityUpdate)

        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL/${product.productId}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alcatra Bovina"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.quantityInStock").value(250))
            .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value(48.0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.supplierName").value("CASA DAS CARNES CARIOCA"))
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("updateQuantityStockProduct returns product update in decremented when existing product in the database")
    fun `updateQuantityStockProduct_ReturnsProductUpdateInDecremented_WhenExistingProductInTheDatabase`() {

        val productQuantityUpdate: QuantityProductRequest =
            BuildQuantityProductRequest.buildQuantityProductRequestDecrement()

        val json = objectMapper.writeValueAsString(productQuantityUpdate)

        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL/${product.productId}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alcatra Bovina"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.quantityInStock").value(50))
            .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value(48.0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.supplierName").value("CASA DAS CARNES CARIOCA"))
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

    @Test
    @DisplayName("updateQuantityStockProduct returns OperationNotPerformedException when quantity in stock a is less than zero")
    fun `updateQuantityStockProduct_ReturnsOperationNotPerformedException_When_QuantityInStockAIsLessThanZero`() {

        val productQuantityUpdate: QuantityProductRequest =
            BuildQuantityProductRequest.buildQuantityProductRequestDecrementQuantityInStockAIsLessThanZero()

        val json = objectMapper.writeValueAsString(productQuantityUpdate)

        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL/${product.productId}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity)
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(422))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Quantity exceeds minimum limit of zero"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("UNPROCESSABLE_ENTITY"))
            .andDo(
                MockMvcResultHandlers.print()
            )
    }

}

