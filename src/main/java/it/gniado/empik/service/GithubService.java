package it.gniado.empik.service;

import it.gniado.empik.exception.GithubException;
import it.gniado.empik.exception.UserNotFoundException;
import it.gniado.empik.model.GithubUser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GithubService {

    @NonNull private final RestTemplate restTemplate;

    @Value(value = "${github.uri}")
    private String githubUri;

    public GithubUser getUser(String login) {
        var uri = prepareUri(login);
        try {
            return restTemplate.getForObject(uri, GithubUser.class);
        } catch (HttpClientErrorException ex){
            if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new UserNotFoundException("User " + login + " not found!");
            }
            throw new GithubException("Something went wrong when approaching GithubAPI.", ex.getStatusCode());
        }
    }

    private String prepareUri(String login){
        return String.format(githubUri, login);
    }
}
