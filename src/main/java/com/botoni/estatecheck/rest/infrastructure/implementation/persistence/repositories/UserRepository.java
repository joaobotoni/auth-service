package com.botoni.estatecheck.rest.infrastructure.implementation.persistence.repositories;

import com.botoni.estatecheck.rest.infrastructure.implementation.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
