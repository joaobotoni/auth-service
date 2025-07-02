package com.botoni.authservice.application;

import com.botoni.authservice.adapter.TokenAdapter;
import com.botoni.authservice.core.domain.User;
import com.botoni.authservice.infrastructure.persistence.entities.UserEntity;
import com.botoni.authservice.core.usecase.TokenUseCase;
import com.botoni.authservice.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService implements TokenUseCase {

    private final TokenAdapter tokenAdapter;
    private final UserMapper mapper;

    @Autowired
    public TokenService(TokenAdapter tokenAdapter, UserMapper mapper) {
        this.tokenAdapter = tokenAdapter;
        this.mapper = mapper;
    }

    @Override
    public String generateToken(User user) {
        return tokenAdapter.generateToken(mapper.toEntity(user));
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
