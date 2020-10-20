package com.demo.stockex.services;

import com.demo.stockex.domain.Role;
import com.demo.stockex.domain.User;
import com.demo.stockex.repositories.RoleRepository;
import com.demo.stockex.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    UserService userService;

    User user1;
    User user2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, roleRepository, bCryptPasswordEncoder);

        user1 = new User();
        user2 = new User();

        user1.setId(1);
        user1.setUsername("Alice");
        user1.setPassword("123");

        user2.setId(2);
        user2.setUsername("Bob");
        user2.setPassword("123");
    }

    @Test
    void getUsers() {
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);
        assertEquals(2, userService.getUsers().size());
    }

    @Test
    void findById() {
        Optional<User> optionalUser = Optional.of(user1);
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);
        assertEquals("Alice", userService.findById(1).getUsername());
    }

    @Test
    void deleteById() {
        userService.deleteById(anyInt());
        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void save() {
        Role role = new Role("ROLE_USER");

        when(userRepository.findByUsername(anyString())).thenReturn(null);
        when(roleRepository.save(any(Role.class))).thenReturn(role);
        when(userRepository.save(any(User.class))).thenReturn(user1);
        assertTrue(userService.save(user1));

    }

    @Test
    void loadUserByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(user1);
        assertEquals("Alice", userService.loadUserByUsername("Alice").getUsername());
    }
}