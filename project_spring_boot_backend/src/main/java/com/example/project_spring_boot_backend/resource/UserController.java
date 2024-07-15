package com.example.project_spring_boot_backend.resource;

import com.example.project_spring_boot_backend.domain.User;
import com.example.project_spring_boot_backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user.getUserName(), user.getPassword());
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        return userService.loginUser(user.getUserName(), user.getPassword());
    }
}