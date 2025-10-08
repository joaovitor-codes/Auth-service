package com.dev.authservice.config.exception;

import com.dev.authservice.exceptions.BusinessRuleException;
import com.dev.authservice.exceptions.DataIntegrityViolationException;
import com.dev.authservice.exceptions.ExceptionResponse;
import com.dev.authservice.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ExceptionResponse> handleBusinessRuleException(BusinessRuleException e) {
        var exception = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.name(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        var exception = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND.name(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        var exception = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.name(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        var exception = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.CONFLICT.name(),
                HttpStatus.CONFLICT.value()
        );
        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }

}


