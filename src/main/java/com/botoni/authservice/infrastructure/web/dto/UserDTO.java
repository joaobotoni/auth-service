package com.botoni.authservice.infrastructure.web.dto;

public record UserDTO(Long id, String firstName, String lastName, String email, String password) {
}
