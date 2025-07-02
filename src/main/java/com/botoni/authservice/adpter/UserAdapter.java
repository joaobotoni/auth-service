package com.botoni.authservice.adpter;

import com.botoni.authservice.core.domain.user.User;
import com.botoni.authservice.core.domain.user.UserDTO;

public interface UserAdapter {
    User save(User user);
    User update(User user);
    User delete(Long id);
    UserDTO validateUserCreated(User user);
    User register(User user);
}
