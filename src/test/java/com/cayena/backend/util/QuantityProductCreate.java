package com.cayena.backend.util;

import com.cayena.backend.dtos.requesties.QuantityProductRequest;
import com.cayena.backend.enums.UpdateType;

public class QuantityProductCreate {

    public static QuantityProductRequest incrementQuantityProduct() {
        return QuantityProductRequest.builder()
                .quantityInStock(ProductResponseCreate.saveProduct().getQuantityInStock())
                .updateType(UpdateType.INCREASE)
                .build();
    }
    public static QuantityProductRequest decrementAboveZeroQuantityProduct() {
        return QuantityProductRequest.builder()
                .quantityInStock(10)
                .updateType(UpdateType.DECREASE)
                .build();
    }
    public static QuantityProductRequest decrementBellowZeroQuantityProduct() {
        return QuantityProductRequest.builder()
                .quantityInStock(100000)
                .updateType(UpdateType.DECREASE)
                .build();
    }
    public static QuantityProductRequest EnumExceptionQuantityProduct() {
        return QuantityProductRequest.builder()
                .quantityInStock(100000)
                .updateType(UpdateType.valueOf("Exception"))
                .build();
    }
}
