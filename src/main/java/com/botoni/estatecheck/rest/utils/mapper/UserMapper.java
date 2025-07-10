package com.botoni.estatecheck.rest.utils.mapper;

import com.botoni.estatecheck.rest.core.domain.User;
import com.botoni.estatecheck.rest.infrastructure.implementation.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toDomain(UserEntity entity);
    UserEntity toEntity(User domain);
}
