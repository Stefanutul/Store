package com.example.Store.Controllers;



import com.example.Store.DTO.ProductRequestDTO;
import com.example.Store.DTO.ProductResponseDTO;
import com.example.Store.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("product/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("product/{id}/price")
    public ResponseEntity<ProductResponseDTO> changePrice(@PathVariable Long id,
                                                          @RequestParam double newPrice) {
        ProductResponseDTO updated = productService.changePrice(id, newPrice);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("product/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.listAllProducts());
    }


    @PostMapping("/api/products")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO createdProduct = productService.addProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }




}


