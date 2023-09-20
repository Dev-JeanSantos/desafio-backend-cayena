package com.cayena.backendkotlin.dominio

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Supplier (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("supplier_id")
    val supplierId: Long,
    @JsonProperty("name_supplier")
    val nameSupplier: String,
    @JsonProperty("date_of_creation")
    val dateOfCreation: LocalDateTime? = LocalDateTime.now(),
    @JsonProperty("date_of_the_last_update")
    val dateOfTheLastUpdate: LocalDateTime? = LocalDateTime.now()
)

