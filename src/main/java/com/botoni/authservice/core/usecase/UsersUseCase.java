package com.botoni.authservice.core.usecase;

import com.botoni.authservice.core.domain.user.User;
import com.botoni.authservice.core.domain.user.UserDTO;

public interface UsersUseCase {
    User save(User user);
    void update(User user);
    void delete(Long id);
    User findUserByEmail(String email);
    UserDTO validateUserCreated(User user);
}
