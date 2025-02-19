package com.example.kansha.controllers;

import com.example.kansha.dtos.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.core.user.OAuth2User;


@RestController
public class OAuthController {

    @GetMapping("/grantcode")
    public ResponseEntity<?> handleGoogleCallback(@RequestParam("code") String code, OAuth2User principal){
        String userName = (String) principal.getAttributes().get("name");
        String userEmail = (String) principal.getAttributes().get("email");

        UserResponse userResponse = new UserResponse(userName, userEmail);

        return ResponseEntity.ok(userResponse);
    }
}
