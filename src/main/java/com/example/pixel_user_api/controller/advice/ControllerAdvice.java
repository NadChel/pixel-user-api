package com.example.pixel_user_api.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));
        return unprocessableEntityWithMessage(message);
    }

    private static ResponseEntity<String> unprocessableEntityWithMessage(String message) {
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(message);
        return response;
    }

    @ExceptionHandler
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        return unprocessableEntityWithMessage(e);
    }

    private static ResponseEntity<String> unprocessableEntityWithMessage(Exception exception) {
        String message = exception.getMessage();
        return unprocessableEntityWithMessage(message);
    }
}
