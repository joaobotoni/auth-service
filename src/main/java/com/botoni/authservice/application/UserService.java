package com.botoni.authservice.application;

import com.botoni.authservice.adpter.UserAdapter;
import com.botoni.authservice.core.domain.user.UserDTO;
import com.botoni.authservice.core.usecase.UsersUseCase;
import com.botoni.authservice.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UsersUseCase {

    private final UserAdapter userAdapter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserAdapter userAdapter, PasswordEncoder passwordEncoder) {
        this.userAdapter = userAdapter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userAdapter.save(user);
    }

    @Override
    public void update(User user) {
        userAdapter.update(user);
    }

    @Override
    public void delete(Long id) {
       userAdapter.delete(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userAdapter.findUserByEmail(email);
    }

    @Override
    public UserDTO validateUserCreated(User user) {
        return userAdapter.validateUserCreated(user);
    }
}
