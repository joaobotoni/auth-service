package com.botoni.authservice.adpter;

import com.botoni.authservice.core.domain.user.UserData;

public interface AuthAdapter {
    String login(UserData data);
}
