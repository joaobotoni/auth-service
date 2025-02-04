package com.backend.system.repositories;

import com.backend.system.models.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository <Product, UUID>{
}
