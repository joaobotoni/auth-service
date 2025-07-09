package com.botoni.estatecheck.rest.adapter;

import com.botoni.estatecheck.rest.core.domain.User;
import com.botoni.authservice.infrastructure.web.dto.UserDTO;

public interface UserAdapter {
    User save(User user);
    User update(User user);
    User delete(Long id);
    User register(User user);
    UserDTO validateUserCreated(User user);
}
