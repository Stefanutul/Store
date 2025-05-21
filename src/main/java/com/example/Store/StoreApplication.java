package com.example.Store;

import com.example.Store.Models.Product;
import com.example.Store.Repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ProductRepository productRepository) {
		return args -> {
			productRepository.save(new Product(null, "The Legend of Zelda", "Action-adventure game by Nintendo", 59.99, 10));
			productRepository.save(new Product(null, "Minecraft", "Sandbox building game", 26.95, 25));
			productRepository.save(new Product(null, "Cyberpunk 2077", "Open world RPG by CD Projekt", 49.99, 7));
		};
	}

}
