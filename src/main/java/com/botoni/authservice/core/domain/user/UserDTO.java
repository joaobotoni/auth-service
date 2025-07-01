package com.botoni.authservice.core.domain.user;

public record UserDTO(Long id, String firstName, String lastName, String email, String password) {
}
