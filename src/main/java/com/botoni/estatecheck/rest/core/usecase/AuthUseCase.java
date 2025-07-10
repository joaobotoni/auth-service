package com.botoni.estatecheck.rest.core.usecase;

import com.botoni.estatecheck.rest.utils.dtos.UserData;

public interface AuthUseCase {
    String login(UserData data);
}
