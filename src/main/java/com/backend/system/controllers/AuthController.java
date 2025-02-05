package com.backend.system.controllers;

import com.backend.system.models.domain.user.User;
import com.backend.system.models.domain.user.UserData;
import com.backend.system.models.dto.UserDTO;
import com.backend.system.repositories.UserRepository;
import com.backend.system.services.UserService;
import com.backend.system.settings.infra.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    /* O AuthenticationManager é responsável por autenticar um usuário com base nas credenciais fornecidas. */

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserData data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword); // Retornar uma Authentication se as credenciais forem válidas

        var token = tokenService.generateToken((User) auth.getPrincipal()); // gera um token a partir do usuario logado
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody User user){
        service.create(user);
        return ResponseEntity.ok().build();
    }
}
