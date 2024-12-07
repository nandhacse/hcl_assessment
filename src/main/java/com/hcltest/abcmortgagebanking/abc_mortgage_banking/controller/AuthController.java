package com.hcltest.abcmortgagebanking.abc_mortgage_banking.controller;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.config.JwtUtil;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request.AuthRequest;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${spring.security.user.name}")
    private String USERNAME;

    @Value("${spring.security.user.password}")
    private String PASSWORD;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        // Replace this with your actual authentication logic
        if (USERNAME.equalsIgnoreCase(request.getUsername()) && PASSWORD.equals(request.getPassword())) {
            String token = JwtUtil.generateToken(request.getUsername(), "ROLE_USER");
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
