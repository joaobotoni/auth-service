package com.backend.system.controllers;

import com.backend.system.models.domain.User;
import com.backend.system.models.dto.UserDTO;
import com.backend.system.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody User user){
        return service.create(user);
    }

}
