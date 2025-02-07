package com.backend.system.services.user;

import com.backend.system.models.domain.user.User;
import com.backend.system.models.dto.UserDTO;
import com.backend.system.models.mapper.UserMapper;
import com.backend.system.repositories.UserRepository;
import com.backend.system.services.mail.MailService;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    MailService service;

    @Autowired
    UserMapper mapper;

    public UserDTO create(User user) throws RuntimeException {
        try {
            if (repository.findByEmail(user.getEmail()) != null) {
                throw new RuntimeException("A user with this email already exists");
            }
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            try {
                repository.save(user);
                service.sendEmail(user.getEmail(), "Registration in the system", "Thank you for registering in our system");
                return mapper.map(user);
            } catch (PersistenceException exception) {
                throw new RuntimeException("Error creating user: " + exception);
            }
        } catch (PersistenceException exception) {
            throw new RuntimeException("An unexpected error occurred:" + exception);
        }
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}