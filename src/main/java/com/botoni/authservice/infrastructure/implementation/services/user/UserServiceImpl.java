package com.botoni.authservice.infrastructure.implementation.services.user;

import com.botoni.authservice.adapter.UserAdapter;
import com.botoni.authservice.core.domain.User;
import com.botoni.authservice.infrastructure.implementation.persistence.entities.UserEntity;
import com.botoni.authservice.infrastructure.web.dto.UserDTO;
import com.botoni.authservice.utils.mapper.UserMapper;
import com.botoni.authservice.infrastructure.implementation.persistence.repositories.UserRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserAdapter, UserDetailsService {

    private final UserMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public User update(User user) {
        UserEntity entity = mapper.toEntity(user);
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public User delete(Long id) {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        repository.deleteById(entity.getId());
        return mapper.toDomain(entity);
    }

    @Override
    public UserDTO validateUserCreated(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("A user with this email already exists");
        }

        UserEntity entity = mapper.toEntity(user);
        entity.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            UserEntity saved = repository.save(entity);
            return mapper.toDTO(mapper.toDomain(saved));
        } catch (PersistenceException e) {
            throw new RuntimeException("Error creating user: " + e.getMessage(), e);
        }
    }

    @Override
    public User register(User user) {
        return this.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
}
