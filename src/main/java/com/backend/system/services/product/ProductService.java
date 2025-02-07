package com.backend.system.services.product;

import com.backend.system.models.domain.product.Product;
import com.backend.system.models.domain.user.User;
import com.backend.system.models.dto.ProductDTO;
import com.backend.system.repositories.ProductRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import for transactions

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public ProductDTO create(Product product) throws RuntimeException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof User user)) {
            throw new RuntimeException("User not authenticated.");
        }
        product.setUserId(user.getId());
        try {
            repository.save(product);
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setCategory(product.getCategory());
            productDTO.setUserId(product.getUserId());
            return productDTO;

        } catch (PersistenceException exception) {
            throw new RuntimeException("Error saving product: " + exception.getMessage(), exception);
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred: " + ex.getMessage(), ex);
        }
    }
}