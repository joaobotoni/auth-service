package com.botoni.authservice.application;

import com.botoni.authservice.adapter.UserAdapter;
import com.botoni.authservice.core.domain.User;
import com.botoni.authservice.infrastructure.persistence.entities.UserEntity;
import com.botoni.authservice.infrastructure.web.dto.UserDTO;
import com.botoni.authservice.core.usecase.UsersUseCase;
import com.botoni.authservice.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UsersUseCase {

    private final UserAdapter userAdapter;
    private final UserMapper mapper;

    @Autowired
    public UserService(UserAdapter userAdapter, UserMapper mapper) {
        this.userAdapter = userAdapter;
        this.mapper = mapper;
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
    public User delete(Long id) {
        return userAdapter.delete(id);
    }

    @Override
    public UserDTO validateUserCreated(User user) {
        return userAdapter.validateUserCreated(user);
    }

    @Override
    public User register(User user) {
        return userAdapter.register(user);
    }
}
