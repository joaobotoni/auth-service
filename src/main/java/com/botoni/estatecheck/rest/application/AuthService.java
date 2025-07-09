package com.botoni.estatecheck.rest.application;

import com.botoni.estatecheck.rest.adapter.AuthAdapter;
import com.botoni.authservice.infrastructure.web.dto.UserData;
import com.botoni.estatecheck.rest.core.usecase.AuthUseCase;
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
