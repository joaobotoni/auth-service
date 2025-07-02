package com.botoni.authservice.adpter;

import com.botoni.authservice.infrastructure.web.dto.UserData;

public interface AuthAdapter {
    String login(UserData data);
}
