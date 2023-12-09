package it.gniado.empik.controller;

import it.gniado.empik.exception.GithubException;
import it.gniado.empik.model.RestError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class GithubExceptionResolver extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(GithubExceptionResolver.class.getName());

    @ExceptionHandler(value = {GithubException.class})
    protected ResponseEntity<Object> handle(GithubException ex, HttpServletRequest servletRequest) {
        LOGGER.log(Level.SEVERE, ex.getMessage());
        LOGGER.log(Level.SEVERE, "Status code: " + ex.getStatus());
        return ResponseEntity.badRequest().body(new RestError(ex.getStatus(), ex.getMessage()));
    }
}
