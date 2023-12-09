package it.gniado.empik.service;

import it.gniado.empik.entity.GithubLogin;
import it.gniado.empik.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountingService {

    private final UserRepository userRepository;

    public void incrementCount(String login){
        userRepository.findById(login).ifPresentOrElse(gl -> {
            gl.setRequestCount(gl.getRequestCount() + 1);
            userRepository.save(gl);
        }, () -> {
            GithubLogin gl = new GithubLogin(login, 1);
            userRepository.save(gl);
        });
    }
}
