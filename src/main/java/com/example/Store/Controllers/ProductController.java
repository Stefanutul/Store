package com.example.Store.Controllers;


import com.example.Store.DTO.ProductRequestDTO;
import com.example.Store.DTO.ProductResponseDTO;
import com.example.Store.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO dto) {
        ProductResponseDTO created = productService.addProduct(dto);
        return ResponseEntity.ok(created);
    }

    // Get a product by ID
    @GetMapping("product/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }

    // Change price of product
    @PutMapping("product/{id}/price")
    public ResponseEntity<ProductResponseDTO> changePrice(@PathVariable Long id,
                                                          @RequestParam double newPrice) {
        ProductResponseDTO updated = productService.changePrice(id, newPrice);
        return ResponseEntity.ok(updated);
    }

    // List all products
    @GetMapping("product/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.listAllProducts());
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}


