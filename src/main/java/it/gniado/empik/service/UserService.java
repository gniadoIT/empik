package it.gniado.empik.service;

import it.gniado.empik.model.GithubUser;
import it.gniado.empik.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final CountingService countingService;
    private final GithubService githubService;

    public User getUser(String login) {
        countingService.incrementCount(login);
        GithubUser githubUser = githubService.getUser(login);
        return createUser(githubUser);
    }

    private User createUser(GithubUser githubUser) {
        Double calculations = calculateFollowersWithPublicRepos(githubUser);
        return new User.Builder().withGithubUser(githubUser).withCalculations(calculations).build();
    }

    private Double calculateFollowersWithPublicRepos(GithubUser result) {
        Double calculations = null;
        if (result.getFollowers() != 0) {
            calculations = 6.0 / result.getFollowers() * (2 + result.getPublic_repos());
        }
        return calculations;
    }


}
