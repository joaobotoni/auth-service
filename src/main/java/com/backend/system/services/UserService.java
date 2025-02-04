package com.backend.system.services;

import com.backend.system.models.domain.User;
import com.backend.system.models.dto.UserDTO;
import com.backend.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    // Creates a new user in the database by registering the provided information.
    public ResponseEntity<UserDTO> create(User user) {
        if (repository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("A user with this email already exists");
        } else {
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            repository.save(user);
            UserDTO userToUserDTO = new UserDTO(user.getUsername(), user.getEmail(), user.getPassword());
            return ResponseEntity.ok(userToUserDTO);
        }
    }

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
