package com.botoni.estatecheck.rest.core.usecase;

import com.botoni.estatecheck.rest.core.domain.User;

import java.time.Instant;

public interface TokenUseCase {
    String generateToken(User user);
    String validateToken(String token);
    Instant expirationDate();
}
