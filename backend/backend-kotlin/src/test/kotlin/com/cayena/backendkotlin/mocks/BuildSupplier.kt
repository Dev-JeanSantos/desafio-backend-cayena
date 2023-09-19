package com.cayena.backendkotlin.mocks

import com.cayena.backendkotlin.dominio.Supplier
import java.time.LocalDateTime

object BuildSupplier {
    fun buildSupplier(
        supplierId: Long = 1,
        name: String = "Casa das Carnes",
        dateOfCreation: LocalDateTime = LocalDateTime.now()
    ) = Supplier(
        supplierId = supplierId,
        nameSupplier = name,
        dateOfCreation = dateOfCreation
    )
}