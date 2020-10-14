package com.demo.stockex.services;

import com.demo.stockex.domain.User;
import com.demo.stockex.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserServiceImplTest {

    @Autowired
    UserRepository userRepository;

    UserService userService;

    User user;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
        user = new User();
        user.setName("Bob");
        userRepository.save(user);
    }

    @Test
    void getUsers() {
        assertEquals(1, userService.getUsers().size());
    }

    @Test
    void findById() {
        assertEquals("Bob", userService.findById(user.getId()).getName());
    }
}