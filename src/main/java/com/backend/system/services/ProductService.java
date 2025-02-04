package com.backend.system.services;

import com.backend.system.models.domain.Product;
import com.backend.system.models.dto.ProductDTO;
import com.backend.system.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    // Cria um novo produto no banco de dados registrando as informações fornecidas.
    public ResponseEntity<ProductDTO> create(Product product) throws RuntimeException {
        repository.save(product);
        ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getPrice());
        return ResponseEntity.ok(dto);
    }

}
