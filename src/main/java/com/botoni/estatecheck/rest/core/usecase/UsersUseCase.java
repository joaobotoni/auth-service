package com.botoni.estatecheck.rest.core.usecase;

import com.botoni.estatecheck.rest.core.domain.User;

import java.util.UUID;

public interface UsersUseCase {
    User save(User user);
    User update(User user);
    User delete(UUID id);
    User register(User user);
    User validateUserCreated(User user);
}
