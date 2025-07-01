package com.botoni.authservice.core.mapper;

import com.botoni.authservice.core.domain.User;
import com.botoni.authservice.core.domain.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDTO map(User user);
}
