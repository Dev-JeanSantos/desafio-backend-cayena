package com.cayena.backendkotlin.dominio

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("product_id")
    val productId: Long? = null,
    var name: String,
    @JsonProperty("quantity_in_stock")
    var quantityInStock: Int,
    @JsonProperty("unit_price")
    var unitPrice: BigDecimal,
    @JsonProperty("date_of_creation")
    val dateOfCreation: LocalDateTime? = LocalDateTime.now(),
    @JsonProperty("date_of_the_last_update")
    var dateOfTheLastUpdate: LocalDateTime? = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "supplier_id")
//    @JsonProperty("supplier_id")
    var supplier: Supplier
)
