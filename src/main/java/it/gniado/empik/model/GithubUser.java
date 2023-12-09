package it.gniado.empik.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
public class GithubUser {

    @Getter
    private final String login;
    @Getter
    private final Long id;
    @Getter
    private final String avatar_url;
    @Getter
    private final String type;
    @Getter
    private final String name;
    @Getter
    private final Integer public_repos;
    @Getter
    private final Long followers;
    @Getter
    private final Date created_at;
}
