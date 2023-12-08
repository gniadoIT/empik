package it.gniado.empik.controller;

import it.gniado.empik.service.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RestController {

    private final RestService restService;

    public RestController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/users/{login}")
    public ResponseEntity qwe(@PathVariable String login){
        restService.getGithubUser(login);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
