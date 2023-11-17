package com.ontop.challenge.service;

import com.ontop.challenge.model.User;
import com.ontop.challenge.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldFindUser() {
        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
        User user = userService.findUserById(1L);
        Assertions.assertNotNull(user);
    }

    @Test
    public void shouldNotFindUser() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        User user = userService.findUserById(1L);
        Assertions.assertNull(user);
    }
}
