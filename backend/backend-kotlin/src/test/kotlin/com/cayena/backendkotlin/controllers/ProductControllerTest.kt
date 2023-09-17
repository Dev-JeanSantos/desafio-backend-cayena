package com.cayena.backendkotlin.controllers

import com.cayena.backendkotlin.dtos.requests.ProductRequest
import com.cayena.backendkotlin.mocks.BuildProductRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
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

    companion object{
        const val URL: String = "/api/v1/products"
    }

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
        println(requestConverterString)
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
}