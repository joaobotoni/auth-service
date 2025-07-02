package com.botoni.authservice.core.usecase;

import com.botoni.authservice.infrastructure.web.dto.UserData;

public interface AuthUseCase {
    String login(UserData data);
}
