package com.botoni.authservice.application;

import com.botoni.authservice.adpter.UserAdapter;
import com.botoni.authservice.core.domain.user.User;
import com.botoni.authservice.core.domain.user.UserDTO;
import com.botoni.authservice.core.domain.user.UserData;
import com.botoni.authservice.core.usecase.UsersUseCase;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UsersUseCase {

    private final UserAdapter userAdapter;

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
