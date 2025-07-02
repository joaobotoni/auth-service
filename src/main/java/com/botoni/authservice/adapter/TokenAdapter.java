package com.botoni.authservice.adapter;

import com.botoni.authservice.core.domain.User;
import com.botoni.authservice.infrastructure.persistence.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;

public interface TokenAdapter {
    String generateToken(UserDetails user);

    String validateToken(String token);

    Instant expirationDate();

}
