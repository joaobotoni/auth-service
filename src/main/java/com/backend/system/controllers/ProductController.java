package com.backend.system.controllers;

import com.backend.system.models.domain.product.Product;
import com.backend.system.models.dto.ProductDTO;
import com.backend.system.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@RequestBody Product product){
        ProductDTO createdProductDTO = service.create(product);
        return ResponseEntity.ok(createdProductDTO);
    }
}
