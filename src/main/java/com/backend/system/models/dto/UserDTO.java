package com.backend.system.models.dto;

import com.backend.system.models.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String password;
}


