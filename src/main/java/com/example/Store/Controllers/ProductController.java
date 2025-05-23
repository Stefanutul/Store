package com.example.Store.Controllers;

import com.example.Store.DTO.PriceUpdateRequestDTO;
import com.example.Store.DTO.ProductRequestDTO;
import com.example.Store.DTO.ProductResponseDTO;
import com.example.Store.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/product/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/api/product/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.listAllProducts());
    }

    @PostMapping("/api/add/product")
    public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO dto) {
        ProductResponseDTO saved = productService.addProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/api/delete/product/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable Long id) {
        ProductResponseDTO deleted = productService.deleteProductById(id);
        return ResponseEntity.ok(deleted);
    }


    @PutMapping("/api/product/update/price/{id}")
    public ResponseEntity<ProductResponseDTO> changePrice(@PathVariable Long id,
                                                          @RequestBody PriceUpdateRequestDTO request) {
        ProductResponseDTO updated = productService.changePrice(id, request.getPrice());
        return ResponseEntity.ok(updated);
    }

}


