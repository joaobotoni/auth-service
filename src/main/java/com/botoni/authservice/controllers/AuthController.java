package com.botoni.authservice.controllers;


import com.botoni.authservice.application.UserService;
import com.botoni.authservice.core.domain.user.User;
import com.botoni.authservice.core.domain.user.UserData;
import com.botoni.authservice.infrastructure.security.TokenPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final TokenPresenter tokenPresenter;

    @Autowired
    public AuthController(UserService service, AuthenticationManager authenticationManager, TokenPresenter tokenService, TokenPresenter tokenPresenter) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.tokenPresenter = tokenPresenter;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserData data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenPresenter.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User newUser = service.save(user);
        return ResponseEntity.ok(newUser);
    }
}
