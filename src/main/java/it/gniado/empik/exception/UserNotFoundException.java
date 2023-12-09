package it.gniado.empik.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserNotFoundException extends RuntimeException{

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
