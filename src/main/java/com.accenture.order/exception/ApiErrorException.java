package com.accenture.order.exception;

import brave.Span;
import brave.Tracer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ApiErrorException {

    @Autowired
    private Tracer tracer;

    private static final Logger logger = LoggerFactory.getLogger(ApiErrorException.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationError(MethodArgumentNotValidException ex)
    {
        Span span = tracer.currentSpan();
        String traceID = "";
        if (span != null) {
            logger.info("Trace ID for Invalid Input: {}", span.context().traceId(),ex);
            traceID = String.valueOf(span.context().traceId());
        }

        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST,
                "NOK",1, traceID);
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        errorResponse.setErrors(errors);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleIDNotFoundExceptionError(OrderNotFoundException ex)
    {
        Span span = tracer.currentSpan();
        String traceID ="";
        if (span != null) {
            logger.info("Trace ID for OrderNotFoundException: {}", span.context().traceId(),ex);
            traceID = String.valueOf(span.context().traceId());
        }

        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.NOT_FOUND,
                "NOK",1,traceID);
        List<String> errors = Collections.singletonList(ex.getMessage());
        errorResponse.setErrors(errors);

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralExceptionError(Exception ex)
    {
        Span span = tracer.currentSpan();
        String traceID = "";
        if (span != null) {
            logger.info("Trace ID for Exception errors: {}", span.context().traceId(),ex);
            traceID = String.valueOf(span.context().traceId());
        }

        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR,
                "NOK",1,traceID);
        List<String> errors = Collections.singletonList(ex.getMessage());
        errorResponse.setErrors(errors);

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeExceptionError(RuntimeException ex)
    {
        Span span = tracer.currentSpan();
        String traceID = "";
        if (span != null) {
            logger.info("Trace ID for Runtime Exception errors: {}", span.context().traceId(),ex);
            traceID = String.valueOf(span.context().traceId());
        }

        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR,
                "NOK",1,traceID);
        List<String> errors = Collections.singletonList(ex.getMessage());
        errorResponse.setErrors(errors);

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleWizardInactiveExceptionError(BadRequestException ex)
    {
        Span span = tracer.currentSpan();
        String traceID ="";
        if (span != null) {
            logger.info("Trace ID for OrderNotFoundException: {}", span.context().traceId(),ex);
            traceID = String.valueOf(span.context().traceId());
        }

        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST,
                "NOK",1,traceID);
        List<String> errors = Collections.singletonList(ex.getMessage());
        errorResponse.setErrors(errors);

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleClientErrorExceptionError(HttpClientErrorException ex) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ErrorResponseException errorResponse = objectMapper.readValue(ex.getResponseBodyAsString(),
                ErrorResponseException.class);

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), ex.getRawStatusCode());
    }
}
