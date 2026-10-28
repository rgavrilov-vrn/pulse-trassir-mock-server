package ru.agroeco.pulse.trassirmock.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.agroeco.pulse.trassirmock.application.TrassirMockFacade;
import ru.agroeco.pulse.trassirmock.session.SessionNotFoundException;

@RestControllerAdvice
public class MockExceptionHandler {
    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleSessionNotFound(SessionNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage()));
    }

    @ExceptionHandler(TrassirMockFacade.InvalidPasswordException.class)
    public ResponseEntity<ProblemDetail> handleInvalidPassword(TrassirMockFacade.InvalidPasswordException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage()));
    }
}
