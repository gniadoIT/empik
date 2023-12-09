package it.gniado.empik.service;

import it.gniado.empik.entity.GithubLogin;
import it.gniado.empik.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CountingServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CountingService countingService;

    @Test
    void testAddNotCheckedYet(){
        // GIVEN
        String login = RandomString.make(5);
        doReturn(Optional.ofNullable(null)).when(userRepository).findById(anyString());

        // WHEN
        countingService.incrementCount(login);

        // THEN
        verify(userRepository).findById(login);
        verify(userRepository).save(refEq(new GithubLogin(login, 1)));
    }

    @Test
    void testIncrementCount(){
        // GIVEN
        String login = RandomString.make(5);
        long count = new Random().nextLong();
        GithubLogin returnedEntity = new GithubLogin(login, count);
        GithubLogin entityExpectedToSave = new GithubLogin(login, count+1);
        doReturn(Optional.ofNullable(returnedEntity)).when(userRepository).findById(anyString());

        // WHEN
        countingService.incrementCount(login);

        // THEN
        verify(userRepository).findById(login);
        verify(userRepository).save(refEq(entityExpectedToSave));
    }



}
