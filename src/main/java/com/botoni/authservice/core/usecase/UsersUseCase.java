package com.botoni.authservice.core.usecase;

import com.botoni.authservice.core.domain.User;
import com.botoni.authservice.core.domain.UserDTO;

public interface UsersUseCase {
    void save(User user);
    void update(User user);
    void delete(Long id);
    User findUserByEmail(String email);
    UserDTO validateUserCreated(User user);
}
