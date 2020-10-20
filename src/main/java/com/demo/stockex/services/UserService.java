package com.demo.stockex.services;

import com.demo.stockex.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User findById(Integer id);

    void deleteById(Integer id);

    boolean save(User user);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
