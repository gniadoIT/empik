package it.gniado.empik.service;

import it.gniado.empik.exception.GithubException;
import it.gniado.empik.exception.UserNotFoundException;
import it.gniado.empik.model.GithubUser;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class GithubServiceTest {

    private static final String GITHUB_URI_FIELD = "githubUri";
    private static final String URI = "uri";
    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;
    @Mock
    private Mono mono;

    @InjectMocks
    private GithubService githubService;

    @BeforeEach
    void setup(){
        ReflectionTestUtils.setField(githubService, GITHUB_URI_FIELD, URI);
    }

    @Test
    void lol(){
        // GIVEN
        GithubUser githubUser = new GithubUser("githubuser", 1L, "http://ava.tar", "User", "User", 1, 2L, new Date());
        mockReturningUser(githubUser);

        // WHEN
        GithubUser user = githubService.getUser("githubuser");

        // THEN
        Assert.assertEquals(githubUser, user);
    }

    @Test
    void testUserNotFound(){
        // GIVEN
        var expectedException = new WebClientResponseException(404, "User not found", null, null, null);
        mockRestError(expectedException);

        // WHEN
        UserNotFoundException exception = Assert.assertThrows(UserNotFoundException.class, () -> {
            githubService.getUser("githubuser");
        });

        // THEN
        var actual = exception.getMessage();
        var expected = "User githubuser not found!";
        assertTrue(actual.equals(expected));
    }

    @Test
    void testGithubException(){
        // GIVEN
        var expectedException = new WebClientResponseException(403, "Forbidden", null, null, null);
        mockRestError(expectedException);

        // WHEN
        GithubException exception = Assert.assertThrows(GithubException.class, () -> githubService.getUser("githubuser"));

        // THEN
        var actual = exception.getMessage();
        var expected = "Something went wrong when approaching GithubAPI.";
        var actualStatusCode = exception.getStatus();
        var expectedStatusCode = expectedException.getStatusCode();
        assertTrue(actual.equals(expected));
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    void mockReturningUser(GithubUser githubUser){
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString(), anyString());
        doReturn(responseSpec).when(requestHeadersSpec).retrieve();
        doReturn(mono).when(responseSpec).bodyToMono((Class<Object>) any());
        doReturn(githubUser).when(mono).block();
    }

    void mockRestError(WebClientResponseException exception){
        doReturn(requestHeadersUriSpec).when(webClient).get();
        doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(anyString(), anyString());
        doThrow(exception).when(requestHeadersSpec).retrieve();
    }
}
