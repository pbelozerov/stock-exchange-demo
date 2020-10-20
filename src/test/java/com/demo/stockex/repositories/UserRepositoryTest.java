package com.demo.stockex.repositories;

import com.demo.stockex.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("Bob");
        user.setPassword("123");
    }

    @Test
    void findByUsername() {
        userRepository.save(user);
        assertEquals("Bob", userRepository.findByUsername("Bob").getUsername());
    }
}