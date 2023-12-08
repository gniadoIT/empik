package it.gniado.empik.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GithubLogin {

    @Id
    @Column(name="LOGIN")
    private String login;

    @Column(name = "REQUEST_COUNT")
    private long requestCount;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(long requestCount) {
        this.requestCount = requestCount;
    }
}
