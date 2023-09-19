package com.cayena.backendkotlin.dominio

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Supplier (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val supplierId: Long,
    val nameSupplier: String,
    val dateOfCreation: LocalDateTime? = LocalDateTime.now(),
    val dateOfTheLastUpdate: LocalDateTime? = LocalDateTime.now()
)

