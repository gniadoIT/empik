package it.gniado.empik.controller;

import it.gniado.empik.model.User;
import it.gniado.empik.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping(value = "/users/{login}", produces = {"application/json"})
    public User getUser(@PathVariable String login){
        return userService.getUser(login);
    }

}
