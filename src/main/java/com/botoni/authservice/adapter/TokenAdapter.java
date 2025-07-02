package com.botoni.authservice.adapter;

import com.botoni.authservice.core.domain.model.User;

import java.time.Instant;

public interface TokenAdapter {
    String generateToken(User user);

    String validateToken(String token);

    Instant expirationDate();

}
