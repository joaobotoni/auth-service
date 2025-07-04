package com.botoni.authservice.infrastructure.implementation.persistence.repositories;

import com.botoni.authservice.infrastructure.implementation.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
