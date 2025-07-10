package com.botoni.estatecheck.rest.adapter;

import com.botoni.estatecheck.rest.utils.dtos.UserData;

public interface AuthAdapter {
    String login(UserData data);
}
