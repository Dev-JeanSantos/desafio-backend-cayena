package com.cayena.backend.util;

import com.cayena.backend.entities.Supplier;

import java.time.LocalDateTime;

public class SupplierResponseCreate {

    public static Supplier saveSupplier() {
        return Supplier.builder()
                .suplierId(1L)
                .name("Mercadao de Madureira")
                .dateOfCreation(LocalDateTime.now())
                .dateOfTheLastUpdate(LocalDateTime.now())
                .build();
    }
}