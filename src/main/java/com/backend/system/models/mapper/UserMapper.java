package com.backend.system.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import com.backend.system.models.dto.UserDTO;
import com.backend.system.models.domain.user.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDTO map(User user);
}
