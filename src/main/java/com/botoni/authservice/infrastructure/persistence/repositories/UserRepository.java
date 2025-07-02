package com.botoni.authservice.infrastructure.persistence.repositories;

import com.botoni.authservice.core.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
