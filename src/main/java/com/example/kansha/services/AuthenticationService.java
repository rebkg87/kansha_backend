package com.example.kansha.services;

import com.example.kansha.dtos.LoginUserDto;
import com.example.kansha.dtos.RegisterUserDto;
import com.example.kansha.dtos.UserResponse;
import com.example.kansha.models.User;
import com.example.kansha.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public User signup(RegisterUserDto input) {
        User user = new User();
                user.setName(input.getName());
                user.setEmail(input.getEmail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate (LoginUserDto input){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

    public UserResponse saveOrUpdateUser(Map<String, Object> userAtrributes){
        String email = (String) userAtrributes.get("email");
        User user = userRepository.findByEmail(email).orElse(new User());

        user.setName((String) userAtrributes.get("name"));
        user.setEmail(email);

        user = userRepository.save(user);

        String token =  jwtService.generateToken(user);
        return new UserResponse(user.getName(), user.getEmail(), token);

    }
}
