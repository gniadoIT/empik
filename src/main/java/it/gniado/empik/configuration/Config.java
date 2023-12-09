package it.gniado.empik.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    @Value(value = "${github.header.auth.value}")
    private String githubHeaderAuthValue;
    @Value(value = "${github.header.version.name}")
    private String githubHeaderVersionName;
    @Value(value = "${github.header.version.value}")
    private String githubHeaderVersionValue;

    @Bean
    public WebClient webClient(){
        WebClient client = WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, githubHeaderAuthValue)
                .defaultHeader(githubHeaderVersionName, githubHeaderVersionValue)
                .build();
        return client;
    }

}
