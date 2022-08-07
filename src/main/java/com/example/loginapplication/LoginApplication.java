package com.example.loginapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class LoginApplication {

    public static void main(String[] args) {
//        SpringApplication.run(LoginApplication.class, args);
        SpringApplication application = new SpringApplication(LoginApplication.class);
        Properties properties = new Properties();
        properties.put("lambda.application", "true");
        application.setDefaultProperties(properties);
        application.run(args);
    }

}
