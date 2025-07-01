package com.botoni.authservice.adpter;

import com.botoni.authservice.core.domain.user.User;

import java.time.Instant;

public interface TokenAdapter {
    String generateToken(User user);

    String validateToken(String token);

    Instant expirationDate();

}
