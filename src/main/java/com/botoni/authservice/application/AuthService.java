package com.botoni.authservice.application;

import com.botoni.authservice.adapter.AuthAdapter;
import com.botoni.authservice.infrastructure.web.dto.UserData;
import com.botoni.authservice.core.usecase.AuthUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthUseCase {

    private final AuthAdapter authAdapter;

    @Autowired
    public AuthService(AuthAdapter authAdapter) {
        this.authAdapter = authAdapter;
    }

    @Override
    public String login(UserData data) {
        return authAdapter.login(data);
    }
}
