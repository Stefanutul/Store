package com.example.Store;

import com.example.Store.Models.Product;
import com.example.Store.Repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ProductRepository productRepository) {
		return args -> {
			productRepository.save(Product.builder()
					.name("The Legend of Zelda")
					.description("Action-adventure game by Nintendo")
					.price(59.99)
					.dateAdded(LocalDateTime.now())
					.lastModified(LocalDateTime.now())
					.build());

			productRepository.save(Product.builder()
					.name("Minecraft")
					.description("Sandbox building game")
					.price(26.95)
					.dateAdded(LocalDateTime.now())
					.lastModified(LocalDateTime.now())
					.build());

			productRepository.save(Product.builder()
					.name("Witcher 3")
					.description("GeRaLt ")
					.price(40.95)
					.dateAdded(LocalDateTime.now())
					.lastModified(LocalDateTime.now())
					.build());


		};
	}
}
