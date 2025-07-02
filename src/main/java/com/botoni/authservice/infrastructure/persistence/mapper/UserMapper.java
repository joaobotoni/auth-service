package com.botoni.authservice.infrastructure.persistence.mapper;

import com.botoni.authservice.core.domain.model.User;
import com.botoni.authservice.infrastructure.web.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDTO map(User user);
}
