package com.demo.stockex.services;

import com.demo.stockex.domain.User;

import java.util.Set;

public interface UserService {

    Set<User> getUsers();

    User findById(Integer id);

    void deleteById(Integer id);

    User save(User user);

}
