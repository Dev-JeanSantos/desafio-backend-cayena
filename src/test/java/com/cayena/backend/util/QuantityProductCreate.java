package com.cayena.backend.util;

import com.cayena.backend.dtos.requesties.QuantityProductRequest;
import com.cayena.backend.enums.UpdateType;

public class QuantityProductCreate {

    public static QuantityProductRequest updateQuantityProduct() {
        return QuantityProductRequest.builder()
                .quantityInStock(ProductResponseCreate.saveProduct().getQuantityInStock())
                .updateType(UpdateType.INCREASE)
                .build();
    }
}