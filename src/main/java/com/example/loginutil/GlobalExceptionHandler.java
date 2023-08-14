package com.example.loginutil;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String errorMessage = extractErrorMessage(ex);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    public String extractErrorMessage(DataIntegrityViolationException ex) {
        Throwable rootCause = ex.getRootCause();
        if (rootCause != null) {
            return rootCause.getMessage();
        }
        return "Data integrity violation occurred.";
    }
}
