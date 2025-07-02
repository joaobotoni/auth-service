package com.botoni.authservice.application;

import com.botoni.authservice.adapter.TokenAdapter;
import com.botoni.authservice.core.domain.model.User;
import com.botoni.authservice.core.usecase.TokenUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService implements TokenUseCase {

    private final TokenAdapter tokenAdapter;

    @Autowired
    public TokenService(TokenAdapter tokenAdapter) {
        this.tokenAdapter = tokenAdapter;
    }

    @Override
    public String generateToken(User user) {
        return tokenAdapter.generateToken(user);
    }

    @Override
    public String validateToken(String token) {
        return tokenAdapter.validateToken(token);
    }

    @Override
    public Instant expirationDate() {
        return tokenAdapter.expirationDate();
    }
}
