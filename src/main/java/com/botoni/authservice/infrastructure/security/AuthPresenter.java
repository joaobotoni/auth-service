package com.botoni.authservice.infrastructure.security;

import com.botoni.authservice.adpter.AuthAdapter;
import com.botoni.authservice.core.domain.user.User;
import com.botoni.authservice.core.domain.user.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthPresenter implements AuthAdapter {

    private final AuthenticationManager authenticationManager;
    private final TokenPresenter tokenPresenter;

    @Autowired
    public AuthPresenter(AuthenticationManager authenticationManager, TokenPresenter tokenPresenter) {
        this.authenticationManager = authenticationManager;
        this.tokenPresenter = tokenPresenter;
    }

    @Override
    public String login(UserData data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(authToken);
        return tokenPresenter.generateToken((User) auth.getPrincipal());
    }
}
