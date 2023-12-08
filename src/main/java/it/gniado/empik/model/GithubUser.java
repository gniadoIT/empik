package it.gniado.empik.model;

import java.util.Date;

public class GithubUser {

    private final String login;
    private final Long id;
    private final String node_id;
    private final String avatar_url;
    private final String gravatar_id;
    private final String url;
    private final String html_url;
    private final String followers_url;
    private final String following_url;
    private final String gists_url;
    private final String starred_url;
    private final String subscriptions_url;
    private final String organizations_url;
    private final String repos_url;
    private final String events_url;
    private final String received_events_url;
    private final String type;
    private final Boolean site_admin;
    private final String name;
    private final String company;
    private final String blog;
    private final String location;
    private final String email;
    private final Boolean hireable;
    private final String bio;
    private final String twitter_username;
    private final Integer public_repos;
    private final Integer public_gists;
    private final Long followers;
    private final Long following;
    private final Date created_at;
    private final Date updated_at;

    public GithubUser(String login, Long id, String node_id, String avatar_url, String gravatar_id, String url, String html_url, String followers_url, String following_url, String gists_url, String starred_url, String subscriptions_url, String organizations_url, String repos_url, String events_url, String received_events_url, String type, Boolean site_admin, String name, String company, String blog, String location, String email, Boolean hireable, String bio, String twitter_username, Integer public_repos, Integer public_gists, Long followers, Long following, Date created_at, Date updated_at) {
        this.login = login;
        this.id = id;
        this.node_id = node_id;
        this.avatar_url = avatar_url;
        this.gravatar_id = gravatar_id;
        this.url = url;
        this.html_url = html_url;
        this.followers_url = followers_url;
        this.following_url = following_url;
        this.gists_url = gists_url;
        this.starred_url = starred_url;
        this.subscriptions_url = subscriptions_url;
        this.organizations_url = organizations_url;
        this.repos_url = repos_url;
        this.events_url = events_url;
        this.received_events_url = received_events_url;
        this.type = type;
        this.site_admin = site_admin;
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.email = email;
        this.hireable = hireable;
        this.bio = bio;
        this.twitter_username = twitter_username;
        this.public_repos = public_repos;
        this.public_gists = public_gists;
        this.followers = followers;
        this.following = following;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getLogin() {
        return login;
    }

    public Long getId() {
        return id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Integer getPublic_repos() {
        return public_repos;
    }

    public Long getFollowers() {
        return followers;
    }
}
