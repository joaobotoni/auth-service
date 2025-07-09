package com.botoni.estatecheck.rest.core.usecase;

import com.botoni.authservice.infrastructure.web.dto.UserData;

public interface AuthUseCase {
    String login(UserData data);
}
