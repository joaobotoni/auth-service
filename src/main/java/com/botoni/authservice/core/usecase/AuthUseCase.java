package com.botoni.authservice.core.usecase;

import com.botoni.authservice.core.domain.user.UserData;

public interface AuthUseCase {
    String login(UserData data);
}
