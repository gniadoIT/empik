package it.gniado.empik.controller;

import it.gniado.empik.model.User;
import it.gniado.empik.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping(value = "/users/{login}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<User> qwe(@PathVariable String login){
        var user = userService.getUser(login);
        return ResponseEntity.ok(user);
    }

}
