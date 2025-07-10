package com.botoni.estatecheck.rest.infrastructure.implementation.services.user;

import com.botoni.estatecheck.rest.adapter.UserAdapter;
import com.botoni.estatecheck.rest.core.domain.User;
import com.botoni.estatecheck.rest.infrastructure.implementation.persistence.entities.UserEntity;
import com.botoni.estatecheck.rest.utils.mapper.UserMapper;
import com.botoni.estatecheck.rest.infrastructure.implementation.persistence.repositories.UserRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public User delete(UUID id) {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        repository.deleteById(entity.getId());
        return mapper.toDomain(entity);
    }

    @Override
    public User validateUserCreated(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("A user with this email already exists");
        }

        UserEntity entity = mapper.toEntity(user);
        entity.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            return mapper.toDomain(entity);
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
