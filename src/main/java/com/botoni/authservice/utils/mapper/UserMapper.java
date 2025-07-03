package com.botoni.authservice.utils.mapper;

import com.botoni.authservice.core.domain.User;
import com.botoni.authservice.infrastructure.implementation.persistence.entities.UserEntity;
import com.botoni.authservice.infrastructure.web.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toDomain(UserEntity entity);
    UserEntity toEntity(User domain);

    UserDTO toDTO(User domain);
    User toDomain(UserDTO dto);
}
