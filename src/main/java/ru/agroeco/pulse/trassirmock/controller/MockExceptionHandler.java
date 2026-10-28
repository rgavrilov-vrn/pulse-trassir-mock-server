package ru.agroeco.pulse.trassirmock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MockExceptionHandler {
    @ExceptionHandler(TrassirMockController.InvalidSessionException.class)
    public ResponseEntity<ProblemDetail> handleInvalidSession(TrassirMockController.InvalidSessionException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage()));
    }
}
