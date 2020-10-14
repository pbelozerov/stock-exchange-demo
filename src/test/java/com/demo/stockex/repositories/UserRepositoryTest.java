package com.demo.stockex.repositories;

import com.demo.stockex.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void saveAndFindByName() {
        User user = new User();
        user.setName("Bob");
        userRepository.save(user);
        assertEquals("Bob", userRepository.findByName("Bob").get().getName());
    }
}