package com.example.kansha.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProtectedController {

    @GetMapping("/protected-route")
    public ResponseEntity<String> protectedRoute() {
        return ResponseEntity.ok("Acceso permitido a la ruta protegida");
    }
}
