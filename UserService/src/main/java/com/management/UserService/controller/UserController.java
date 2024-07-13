package com.management.UserService.controller;

import com.management.UserService.Entity.User;
import com.management.UserService.security.CustomUserDetails;
import com.management.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody User userDetails) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        Long userId;
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) principal;
            userId = customUserDetails.getUserId();
            System.out.println(userId);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unable to get user details.");
        }
        if(!id.equals(userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you can't update other user details");
        }

        User user = userService.updateUserById(userId, userDetails);

        if (user != null) {
            return ResponseEntity.ok("User updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update user.");
        }
    }

}