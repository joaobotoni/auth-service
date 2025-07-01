package com.botoni.authservice.core.domain;

import com.botoni.authservice.core.domain.type.UserType;

public record UserDTO(Long id, String firstName, String lastName, String email, String password) {
}
