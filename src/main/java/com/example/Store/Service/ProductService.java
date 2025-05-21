package com.example.Store.Service;

import com.example.Store.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(Product product);
    Optional<Product> findProductById(Long id);
    Product updatePrice(Long id, Product updatedProduct);
    List<Product> getAllProducts();
}
