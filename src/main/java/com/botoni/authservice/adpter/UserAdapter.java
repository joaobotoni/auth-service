package com.botoni.authservice.adpter;

import com.botoni.authservice.core.domain.model.User;
import com.botoni.authservice.infrastructure.web.dto.UserDTO;

public interface UserAdapter {
    User save(User user);
    User update(User user);
    User delete(Long id);
    User register(User user);
    UserDTO validateUserCreated(User user);
}
