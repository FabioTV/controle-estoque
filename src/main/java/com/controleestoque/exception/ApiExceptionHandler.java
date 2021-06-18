package com.controleestoque.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException exception) {
        ApiException apiException = new ApiException(
            exception.getMessage(), 
            //exception,
            HttpStatus.BAD_REQUEST,
            ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
