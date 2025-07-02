package com.botoni.authservice.infrastructure.impl.security;

import com.botoni.authservice.adapter.AuthAdapter;
import com.botoni.authservice.core.domain.model.User;
import com.botoni.authservice.infrastructure.web.dto.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthImpl implements AuthAdapter {

    private final AuthenticationManager authenticationManager;
    private final TokenImpl tokenImpl;

    @Autowired
    public AuthImpl(AuthenticationManager authenticationManager, TokenImpl tokenPresenter) {
        this.authenticationManager = authenticationManager;
        this.tokenImpl = tokenPresenter;
    }

    @Override
    public String login(UserData data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(authToken);
        return tokenImpl.generateToken((User) auth.getPrincipal());
    }
}
