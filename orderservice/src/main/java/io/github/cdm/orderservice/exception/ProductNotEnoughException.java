package io.github.cdm.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotEnoughException extends RuntimeException{
    public ProductNotEnoughException(String message) {
        super(message);
    }
}
