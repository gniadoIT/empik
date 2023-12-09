package it.gniado.empik.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GithubLogin {

    @Id
    @Column(name="LOGIN")
    @Getter
    @Setter
    private String login;

    @Column(name = "REQUEST_COUNT")
    @Getter
    @Setter
    private long requestCount;
}
