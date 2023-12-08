package it.gniado.empik.service;

import it.gniado.empik.model.GithubUser;
import it.gniado.empik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private final UserRepository userRepository;

    @Value(value = "${github.uri}")
    private String githubUri;

    public RestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getGithubUser(String login) {
        var uri = prepareUri(login);
        var restTemplate = new RestTemplate();

        Double calculations = null;

        GithubUser result = restTemplate.getForObject(uri, GithubUser.class);
        if (result.getFollowers() != 0) {
            calculations = 6.0 / result.getFollowers() * (2 + result.getPublic_repos());
        }

        System.out.println(result);
    }

    private String prepareUri(String login){
        return String.format(githubUri, login);
    }

}
