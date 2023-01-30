package com.accenture.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

    public static final String INVALID_ID = "Order Id not found";

    public OrderNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
