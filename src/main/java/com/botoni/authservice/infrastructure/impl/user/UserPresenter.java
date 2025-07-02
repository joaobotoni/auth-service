package com.botoni.authservice.infrastructure.impl.user;

import com.botoni.authservice.adpter.UserAdapter;
import com.botoni.authservice.core.domain.model.User;
import com.botoni.authservice.infrastructure.web.dto.UserDTO;
import com.botoni.authservice.infrastructure.persistence.mapper.UserMapper;
import com.botoni.authservice.infrastructure.persistence.repositories.UserRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPresenter implements UserAdapter, UserDetailsService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserPresenter(UserRepository repository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        repository.deleteById(user.getId());
        return user;
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

    @Override
    public User register(User user) {
        return this.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return user;
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private User findUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}
