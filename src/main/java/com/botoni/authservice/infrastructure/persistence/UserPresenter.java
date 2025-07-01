package com.botoni.authservice.infrastructure.persistence;

import com.botoni.authservice.adpter.UserAdapter;
import com.botoni.authservice.core.domain.User;
import com.botoni.authservice.core.domain.UserDTO;
import com.botoni.authservice.core.mapper.UserMapper;
import com.botoni.authservice.repositories.UserRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserPresenter implements UserAdapter, UserDetailsService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Autowired
    public UserPresenter(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public UserDTO validateUserCreated(User user) {
        try {
            if (repository.findByEmail(user.getEmail()) != null) {
                throw new RuntimeException("A user with this email already exists");
            }
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            try {
                repository.save(user);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return user;
    }
}
