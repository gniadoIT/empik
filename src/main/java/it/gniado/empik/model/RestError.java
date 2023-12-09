package it.gniado.empik.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
public class RestError {

    @Getter
    @Setter
    private HttpStatusCode statusCode;

    @Getter
    @Setter
    private String message;

}
