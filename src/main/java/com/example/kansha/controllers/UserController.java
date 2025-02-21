package com.example.kansha.controllers;

import com.example.kansha.models.User;
import com.example.kansha.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/user")
    public ResponseEntity<Optional<User>> getUserByEmail (
        @AuthenticationPrincipal User user ){
            Optional<User> userOptional = userService.findByEmail(user.getEmail());
        return ResponseEntity.ok(userOptional);
    }

    @GetMapping("/home")
    public ResponseEntity<Map<String, String>> home() {
        return ResponseEntity.ok(Map.of("status", "User is authenticated", "message", "Welcome home"));
    }
}