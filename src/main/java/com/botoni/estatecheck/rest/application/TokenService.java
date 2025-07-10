package com.botoni.estatecheck.rest.application;

import com.botoni.estatecheck.rest.adapter.TokenAdapter;
import com.botoni.estatecheck.rest.core.domain.User;
import com.botoni.estatecheck.rest.core.usecase.users.TokenUseCase;
import com.botoni.estatecheck.rest.utils.mapper.UserMapper;
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
