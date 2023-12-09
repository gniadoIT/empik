package it.gniado.empik.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private Long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private Date createdAt;
    private Double calculations;

    public static class Builder{

        private GithubUser githubUser;
        private Double calculations;

        public Builder withGithubUser(GithubUser githubUser){
            this.githubUser = githubUser;
            return this;
        }

        public Builder withCalculations(Double calculations){
            this.calculations = calculations;
            return this;
        }

        public User build(){
            return new User(githubUser.getId(), githubUser.getLogin(), githubUser.getName(), githubUser.getType(), githubUser.getAvatar_url(), githubUser.getCreated_at(), calculations);
        }

    }

}
