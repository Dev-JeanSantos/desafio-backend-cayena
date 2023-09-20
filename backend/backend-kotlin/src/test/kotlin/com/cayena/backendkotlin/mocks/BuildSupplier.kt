package com.cayena.backendkotlin.mocks

import com.cayena.backendkotlin.dominio.Supplier
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

object BuildSupplier {
    fun buildSupplier(
        @JsonProperty("supplier_id")
        supplierId: Long = 2,
        @JsonProperty("name_supplier")
        nameSupplier: String = "GUANABARA",
        dateOfCreation: LocalDateTime = LocalDateTime.now()
    ) = Supplier(
        supplierId = supplierId,
        nameSupplier = nameSupplier,
        dateOfCreation = dateOfCreation
    )
}