package com.botoni.authservice.core.usecase;

import com.botoni.authservice.core.domain.User;
import com.botoni.authservice.infrastructure.persistence.entities.UserEntity;
import com.botoni.authservice.infrastructure.web.dto.UserDTO;

public interface UsersUseCase {
    User save(User user);
    User update(User user);
    User delete(Long id);
    User register(User user);
    UserDTO validateUserCreated(User user);
}
