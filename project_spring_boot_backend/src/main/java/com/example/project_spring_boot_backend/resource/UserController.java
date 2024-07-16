package com.example.project_spring_boot_backend.resource;

import com.example.project_spring_boot_backend.domain.User;
import com.example.project_spring_boot_backend.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//yay controller hai
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @NotNull User user) {
        if (user.getUserName() == null || user.getUserName().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User registeredUser = userService.registerUser(user.getUserName(), user.getPassword());
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @NotNull User user) {
        if (user.getUserName() == null || user.getUserName().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User loggedInUser = userService.loginUser(user.getUserName(), user.getPassword());
        if (loggedInUser != null) {
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        } else {
            // Return a more informative response or use ResponseEntity.notFound()
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: Unauthorized");
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            // You can customize the response based on whether the user was found or not
            return ResponseEntity.notFound().build();
        }
    }
}