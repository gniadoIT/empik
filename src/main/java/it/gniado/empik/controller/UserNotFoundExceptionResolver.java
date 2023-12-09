package it.gniado.empik.controller;

import it.gniado.empik.exception.UserNotFoundException;
import it.gniado.empik.model.RestError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestControllerAdvice
public class UserNotFoundExceptionResolver extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(UserNotFoundExceptionResolver.class.getName());

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<RestError> handle(UserNotFoundException ex, HttpServletRequest servletRequest) {
        LOGGER.log(Level.SEVERE, ex.getMessage());
        return ResponseEntity.badRequest().body(new RestError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }
}
