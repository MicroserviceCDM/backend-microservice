package com.minhvu.inventoryservice.exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends IllegalStateException {

    private String errorCode;

    public ProductServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
