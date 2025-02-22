package com.example.kansha.controllers;

import com.example.kansha.dtos.LoginUserDto;
import com.example.kansha.dtos.RegisterUserDto;
import com.example.kansha.dtos.UserResponseDto;
import com.example.kansha.models.LoginResponse;
import com.example.kansha.models.User;
import com.example.kansha.services.AuthenticationService;
import com.example.kansha.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> register (@RequestBody RegisterUserDto registerUserDto){
        try {
            UserResponseDto registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto){
        UserResponseDto authenticatedUser = authenticationService.authenticate(loginUserDto);
        return ResponseEntity.ok(authenticatedUser);
    }
}
