package com.example.project_spring_boot_backend.service;

import com.example.project_spring_boot_backend.domain.User;
import com.example.project_spring_boot_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
//functions defined here are used in the controller
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepo userRepository;

    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        //log karkay dheko konsi user register ho raha hai
        logger.info("Registering user: {}", userName);
        return userRepository.save(user);
    }

    public User loginUser(String userName, String password) {
        logger.info("Attempting to log in user: {}", userName);
        User user = userRepository.findByUserName(userName);
        if (user != null && user.getPassword().equals(password)) {
            logger.info("Login successful for user: {}", userName);
            return user;
        } else {
            logger.warn("Login failed for user: {}", userName);
            return null;
        }
    }
//get all users
    public List<User> getAllUsers() {
        logger.info("Fetching all registered users");
        return userRepository.findAll();
    }
//get indiviual user by id
    public User getUserById(Long id) {
        logger.info("Fetching user by ID: {}", id);
        return userRepository.findById(id).orElse(null);
    }

}
