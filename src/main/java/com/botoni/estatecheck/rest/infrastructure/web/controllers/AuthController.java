package com.botoni.estatecheck.rest.infrastructure.web.controllers;


import com.botoni.estatecheck.rest.application.AuthService;
import com.botoni.estatecheck.rest.application.UserService;
import com.botoni.estatecheck.rest.core.domain.User;
import com.botoni.authservice.infrastructure.web.dto.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService service, AuthenticationManager authenticationManager, AuthService authService) {
        this.service = service;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserData data) {
        String response = authService.login(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User response = service.register(user);
        return ResponseEntity.ok(response);
    }
}
