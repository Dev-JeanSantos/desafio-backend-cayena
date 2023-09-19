package com.cayena.backendkotlin.dominio

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productId: Long? = null,
    var name: String,
    var quantityInStock: Int,
    var unitPrice: BigDecimal,
    val dateOfCreation: LocalDateTime? = LocalDateTime.now(),
    var dateOfTheLastUpdate: LocalDateTime? = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    var supplier: Supplier
)
