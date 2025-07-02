package com.botoni.authservice.infrastructure.implementation.security;

import com.botoni.authservice.adapter.AuthAdapter;
import com.botoni.authservice.core.domain.User;
import com.botoni.authservice.infrastructure.persistence.entities.UserEntity;
import com.botoni.authservice.infrastructure.web.dto.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthAdapter {

    private final AuthenticationManager authenticationManager;
    private final TokenServiceImpl tokenImpl;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, TokenServiceImpl tokenPresenter) {
        this.authenticationManager = authenticationManager;
        this.tokenImpl = tokenPresenter;
    }

    @Override
    public String login(UserData data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(authToken);
        return tokenImpl.generateToken((UserEntity) auth.getPrincipal());
    }
}
