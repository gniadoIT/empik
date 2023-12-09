package it.gniado.empik.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String type;
    @Getter
    @Setter
    private String avatarUrl;
    @Getter
    @Setter
    private Date createdAt;
    @Getter
    @Setter
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
