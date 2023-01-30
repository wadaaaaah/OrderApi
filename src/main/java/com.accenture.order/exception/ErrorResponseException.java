package com.accenture.order.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponseException {

    private HttpStatus status;
    private String timeStamp;
    private String type;
    private int code;
    private String traceID;
    private List<String> errors;

    public ErrorResponseException()
    {
        LocalDateTime date = LocalDateTime.now();
        timeStamp = String.valueOf(date);
    }

    public ErrorResponseException(HttpStatus status) {
        this();
        this.status = status;
    }

    public ErrorResponseException(HttpStatus status, String type, int code, String traceID) {
        this();
        this.type = type;
        this.status = status;
        this.code = code;
        this.traceID = traceID;
    }

    public ErrorResponseException(HttpStatus status, String timeStamp, String type, int code, String traceID) {
        this.status = status;
        this.timeStamp = timeStamp;
        this.type = type;
        this.code = code;
        this.traceID = traceID;
    }

    public ErrorResponseException(HttpStatus status, String timeStamp, String type, int code,
                                  String traceID, List<String> errors) {
        this.status = status;
        this.timeStamp = timeStamp;
        this.type = type;
        this.code = code;
        this.traceID = traceID;
        this.errors = errors;
    }
}
