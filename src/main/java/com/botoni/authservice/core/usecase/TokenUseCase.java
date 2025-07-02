package com.botoni.authservice.core.usecase;

import com.botoni.authservice.core.domain.User;
import com.botoni.authservice.infrastructure.persistence.entities.UserEntity;

import java.time.Instant;

public interface TokenUseCase {
    String generateToken(User user);
    String validateToken(String token);
    Instant expirationDate();
}
