package com.botoni.estatecheck.rest.adapter;

import com.botoni.estatecheck.rest.core.domain.User;

import java.util.UUID;


public interface UserAdapter {
    User save(User user);
    User update(User user);
    User delete(UUID id);
    User register(User user);
    User validateUserCreated(User user);
}
