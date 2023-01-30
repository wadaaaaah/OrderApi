package com.accenture.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Throwable {

    public static final String BAD_REQUEST = "Bad Request error is encountered";

    public BadRequestException(String errorMessage) {
        super(errorMessage);
    }

}
