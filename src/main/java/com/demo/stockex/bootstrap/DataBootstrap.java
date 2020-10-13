package com.demo.stockex.bootstrap;

import com.demo.stockex.domain.Share;
import com.demo.stockex.domain.User;
import com.demo.stockex.repositories.ShareRepository;
import com.demo.stockex.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final ShareRepository shareRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        userRepository.saveAll(getUsers());
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Share share1 = new Share();
        Share share2 = new Share();
        User user1 = new User();
        User user2 = new User();

        share1.setName("Apple");
        share1.setSymbol("APL");
        share1.setAmount(2);
        share1.setPrice(1.10);
        share1.setUser(user1);

        share2.setName("Google");
        share2.setSymbol("GGL");
        share2.setAmount(5);
        share2.setPrice(2.30);
        share2.setUser(user2);

        user1.setName("Alice");
        user1.getShares().add(share1);

        user2.setName("Bob");
        user2.getShares().add(share2);

        users.add(user1);
        users.add(user2);

        return users;
    }
}
