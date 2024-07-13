package com.management.UserService;

import com.management.UserService.Entity.User;
import com.management.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) {

        String masterAdminUsername = "Apica";
        String masterAdminPassword = "Apica123@";

        String masterAdminRole = "ADMIN";

        boolean exists = userService.userExists(masterAdminUsername);

        if (!exists) {
            User masterAdmin = new User();
            masterAdmin.setUsername(masterAdminUsername);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(masterAdminPassword);
            masterAdmin.setPassword(encodedPassword);
            masterAdmin.setRole(masterAdminRole);
            masterAdmin.setDeleted(false);
            userService.save(masterAdmin);
            System.out.println("Master admin created successfully.");
        } else {
            System.out.println("Master admin already exists.");
        }
    }
}