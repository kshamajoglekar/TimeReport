package com.wave.advice;

import com.wave.exception.ApplicationException;
import com.wave.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity < String > applicationExceptionHandler(final ApplicationException e) {
        return error(e, null);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity < String > validationExceptionHandler(final ValidationException e) {
        return error(e, null);
    }
    private ResponseEntity <String> error(final Exception exception, final HttpStatus httpStatus) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(message, httpStatus);
    }
}