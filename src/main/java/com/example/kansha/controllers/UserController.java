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
    public ResponseEntity<User> authenticatedUser(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmail (
        @AuthenticationPrincipal User user ){
        return userService.findByEmail(user.getEmail())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/home")
    public ResponseEntity<Map<String, String>> userHome(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(Map.of(
                "status", "Authenticated",
                "message", "Welcome " + currentUser.getName()
        ));
    }

}