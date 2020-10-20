package com.demo.stockex.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    //Can be used to add initial data to DB for testing purpose
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    }

    //Test token from iexcloud.io is used
    public static String getToken() throws Exception {
        String token;
        try {
            token = ResourceBundle.getBundle("private", Locale.ENGLISH).getString("token");
        } catch (Exception e) {
            throw new Exception("Can't load token");
        }

        if (token.isEmpty()) {
            throw new Exception("Can't load token");
        }

        return token;
    }
}
