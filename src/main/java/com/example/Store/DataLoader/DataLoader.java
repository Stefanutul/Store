package com.example.Store.DataLoader;

import com.example.Store.Models.Product;
import com.example.Store.Repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(1L, "The Legend of Zelda", "Action-adventure game by Nintendo", 59.99, 10));
        productRepository.save(new Product(2L, "Minecraft", "Sandbox building game", 26.95, 25));
        productRepository.save(new Product(3L, "Cyberpunk 2077", "Open world RPG by CD Projekt", 49.99, 7));
    }
}
