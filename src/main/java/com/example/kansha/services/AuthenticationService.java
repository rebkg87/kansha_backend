package com.example.kansha.services;

import com.example.kansha.dtos.LoginUserDto;
import com.example.kansha.dtos.RegisterUserDto;
import com.example.kansha.dtos.UserResponseDto;
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

    public UserResponseDto signup(RegisterUserDto registerUserDto) {
        userRepository.findByEmail(registerUserDto.getEmail())
                .ifPresent(user -> {
                    throw new RuntimeException("El email ya está registrado");
                });
        User newUser = User.builder()
                .name(registerUserDto.getName())
                .email(registerUserDto.getEmail())
                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                .provider("local")
                .build();

        User savedUser = userRepository.save(newUser);

        String jwtToken = jwtService.generateToken(savedUser);

        return UserResponseDto.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .provider(savedUser.getProvider())
                .token(jwtToken)
                .build();
    }

    public UserResponseDto authenticate (LoginUserDto loginUserDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getEmail(),
                        loginUserDto.getPassword()
                )
        );

        User user = userRepository.findByEmail(loginUserDto.getEmail())
                .orElseThrow(()-> new RuntimeException("Usuario no encontradp"));

        String jwtToken = jwtService.generateToken(user);

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .provider(user.getProvider())
                .token(jwtToken)
                .build();
    }
}
