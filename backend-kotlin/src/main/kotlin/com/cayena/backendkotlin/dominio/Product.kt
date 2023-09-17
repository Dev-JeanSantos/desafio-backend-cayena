package com.cayena.backendkotlin.dominio

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productId: Long? = null,
    val name: String,
    val quantityInStock: Int,
    val unitPrice: BigDecimal,
    val dateOfCreation: LocalDateTime? = LocalDateTime.now(),
    val dateOfThelastUpdate: LocalDateTime? = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    val supplier: Supplier
)