package com.botoni.estatecheck.rest.application;

import com.botoni.estatecheck.rest.adapter.UserAdapter;
import com.botoni.estatecheck.rest.core.domain.User;
import com.botoni.estatecheck.rest.core.usecase.users.UsersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UsersUseCase {

    private final UserAdapter userAdapter;
    @Autowired
    public UserService(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    @Override
    public User save(User user) {
        return userAdapter.save(user);
    }

    @Override
    public User update(User user) {
        return userAdapter.update(user);
    }

    @Override
    public User delete(UUID id) {
        return userAdapter.delete(id);
    }

    @Override
    public User validateUserCreated(User user) {
        return userAdapter.validateUserCreated(user);
    }

    @Override
    public User register(User user) {
        return userAdapter.register(user);
    }
}
