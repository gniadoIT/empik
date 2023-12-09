package it.gniado.empik.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
public class GithubException extends RuntimeException{

    private final String message;
    private final HttpStatusCode status;

    public HttpStatusCode getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
