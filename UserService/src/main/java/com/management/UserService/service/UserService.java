package com.management.UserService.service;

import com.management.UserService.Entity.User;
import com.management.UserService.model.UserEvent;
import com.management.UserService.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserEventProducer userEventProducer;

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        user.setRole("USER");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        String action="REGISTERED";
        String role="USER";
        String id=savedUser.getId().toString();
        userEventProducer.sendKafkaProducer(action,role,id);
        return savedUser;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUsername(user.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        existingUser.setPassword(encodedPassword);
        String action="UPDATE";
        String role="ADMIN";
        userEventProducer.sendKafkaProducer(action,role,String.valueOf(id));
        return userRepository.save(existingUser);
    }

    public boolean deleteUser(Long id) {
        try {
            User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            existingUser.setDeleted(true);
            userRepository.save(existingUser);
            String action="DELETE";
            String role="ADMIN";
            userEventProducer.sendKafkaProducer(action,role,String.valueOf(id));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User registerAdmin(User user) {
        user.setRole("ADMIN");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        String action="REGISTERED";
        String role="ADMIN";
        userEventProducer.sendKafkaProducer(action,role,savedUser.getId().toString());
        return savedUser;
    }

    public boolean userExists(String username) {
        User user=userRepository.findByUsername(username);
        if (ObjectUtils.isEmpty(user)){
            return false;
        }
        return true;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User updateUserById(Long userId, User userDetails) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUsername(userDetails.getUsername());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            String action="UPDATE";
            String role="USER";
            String id=String.valueOf(userId);
            userEventProducer.sendKafkaProducer(action,role,id);
            return userRepository.save(user);
        } else {
            return null;
        }
    }
}