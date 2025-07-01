package com.botoni.authservice.core.usecase;

import com.botoni.authservice.core.domain.user.User;

import java.time.Instant;

public interface TokenUseCase {
    String generateToken(User user);
    String validateToken(String token);
    Instant expirationDate();
}
