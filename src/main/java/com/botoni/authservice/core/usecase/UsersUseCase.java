package com.botoni.authservice.core.usecase;

import com.botoni.authservice.core.domain.user.User;
import com.botoni.authservice.core.domain.user.UserDTO;
import com.botoni.authservice.core.domain.user.UserData;

public interface UsersUseCase {
    User save(User user);
    User update(User user);
    User delete(Long id);
    User register(User user);
    UserDTO validateUserCreated(User user);
}
