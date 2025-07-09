package com.botoni.estatecheck.rest.adapter;

import com.botoni.authservice.infrastructure.web.dto.UserData;

public interface AuthAdapter {
    String login(UserData data);
}
