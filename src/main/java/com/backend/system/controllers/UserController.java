package com.backend.system.controllers;

import com.backend.system.models.domain.User;
import com.backend.system.models.dto.UserDTO;
import com.backend.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/test")
    public String test(){
        return "teste";
    }

}
