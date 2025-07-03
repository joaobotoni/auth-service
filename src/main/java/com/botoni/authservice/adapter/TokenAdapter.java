package com.botoni.authservice.adapter;

import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;

public interface TokenAdapter {
    String generateToken(UserDetails user);

    String validateToken(String token);

    Instant expirationDate();

}
