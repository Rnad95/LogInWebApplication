package com.example.loginapplication.util;

import com.example.loginapplication.Service.UserService;
import com.example.loginapplication.model.MyUsers;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstTimeInitializer implements CommandLineRunner {
    @Autowired
    private UserService userService;
    private Log logger = LogFactory.getLog(FirstTimeInitializer.class);
    @Override
    public void run(String... args) throws Exception {
        if(userService.findAll().isEmpty()){
            logger.info("No Users account Found. Create admin user");
            MyUsers admin = new MyUsers("r.khawatrah13@gmail.com","password");
            userService.save(admin);
        }

    }
}
