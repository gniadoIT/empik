package it.gniado.empik.service;

import it.gniado.empik.exception.GithubException;
import it.gniado.empik.exception.UserNotFoundException;
import it.gniado.empik.model.GithubUser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
public class GithubService {

    @NonNull private final WebClient webClient;

    @Value(value = "${github.uri}")
    private String githubUri;

    public GithubUser getUser(String login) {
        try {

            GithubUser user = webClient.get().uri(githubUri, login)
                    .retrieve().bodyToMono(GithubUser.class).block();

            return user;
        } catch (WebClientResponseException ex){
            if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new UserNotFoundException("User " + login + " not found!");
            }
            throw new GithubException("Something went wrong when approaching GithubAPI.", ex.getStatusCode());
        }
    }
}
