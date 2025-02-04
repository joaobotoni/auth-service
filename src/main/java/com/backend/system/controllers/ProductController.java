package com.backend.system.controllers;

import com.backend.system.models.domain.Product;
import com.backend.system.models.dto.ProductDTO;
import com.backend.system.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@RequestBody Product product){
        return service.create(product);
    }

}
