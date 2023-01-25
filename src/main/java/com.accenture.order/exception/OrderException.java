package com.accenture.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OrderException extends RuntimeException {

    public static final String INVALID_ID = "Order Id not found";

    public OrderException(String errorMessage) {
        super(errorMessage);
    }

}
