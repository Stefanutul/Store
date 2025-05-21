package com.example.Store.Repo;

import com.example.Store.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Add custom queries if needed later
}
