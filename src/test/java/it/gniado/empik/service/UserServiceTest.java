package it.gniado.empik.service;

import it.gniado.empik.model.GithubUser;
import it.gniado.empik.model.User;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Random;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private GithubService githubService;

    @Mock
    private CountingService countingService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup(){
        doNothing().when(countingService).incrementCount(anyString());
    }

    @Test
    void testGetUser(){
        // GIVEN
        var userFromApi = getGithubUser(new Random().nextLong());
        doReturn(userFromApi).when(githubService).getUser(anyString());

        // WHEN
        User user = userService.getUser("");

        // THEN
        assertMappedFields(user, userFromApi);
        Assert.assertEquals(user.getCalculations(), calculate(userFromApi));
    }

    private static void assertMappedFields(User user, GithubUser userFromApi) {
        Assert.assertEquals(user.getId(), userFromApi.getId());
        Assert.assertEquals(user.getName(), userFromApi.getName());
        Assert.assertEquals(user.getAvatarUrl(), userFromApi.getAvatar_url());
        Assert.assertEquals(user.getType(), userFromApi.getType());
        Assert.assertEquals(user.getLogin(), userFromApi.getLogin());
        Assert.assertEquals(user.getCreatedAt(), userFromApi.getCreated_at());
    }

    @Test
    void testGetUserWithNoFollowers(){
        // GIVEN
        var userFromApi = getGithubUser(0L);
        doReturn(userFromApi).when(githubService).getUser(anyString());

        // WHEN
        User user = userService.getUser("");

        // THEN
        assertMappedFields(user, userFromApi);
        Assert.assertNull(user.getCalculations());
    }

    private Double calculate(GithubUser userFromApi) {
        return 6.0 / userFromApi.getFollowers() * (2 + userFromApi.getPublic_repos());
    }

    private GithubUser getGithubUser(Long followers){
        return GithubUser.builder()
                .id(new Random().nextLong())
                .name(RandomString.make(5))
                .avatar_url(RandomString.make(5))
                .type(RandomString.make(5))
                .login(RandomString.make(5))
                .followers(followers)
                .public_repos(new Random().nextInt())
                .created_at(new Date()).build();
    }
}
