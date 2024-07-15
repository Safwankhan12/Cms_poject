package com.example.project_spring_boot_backend.service;
//controller se data leke service me dalne ke liye yeh class banayi hai
import com.example.project_spring_boot_backend.domain.User;
import com.example.project_spring_boot_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepository;
//yahan dependency injection karne ke liye @Autowired ka use kiya hai
    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User loginUser(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
