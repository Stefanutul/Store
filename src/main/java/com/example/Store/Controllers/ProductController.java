package com.example.Store.Controllers;

import com.example.Store.Mapper.ProductMapper;
import com.example.Store.DTO.ProductRequestDTO;
import com.example.Store.DTO.ProductResponseDTO;
import com.example.Store.Models.Product;
import com.example.Store.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO dto) {
        Product savedProduct = productService.addProduct(ProductMapper.toEntity(dto));
        return ResponseEntity.ok(ProductMapper.toDTO(savedProduct));
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}


