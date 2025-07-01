package com.botoni.authservice.adpter;

import com.botoni.authservice.core.domain.user.User;
import com.botoni.authservice.core.domain.user.UserDTO;

public interface UserAdapter {
    User save(User user);

    void update(User user);

    void delete(Long id);

    User findUserByEmail(String email);

    UserDTO validateUserCreated(User user);
}
