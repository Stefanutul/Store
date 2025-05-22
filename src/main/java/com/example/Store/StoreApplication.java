package com.example.Store;

import com.example.Store.Enums.Category;
import com.example.Store.Models.Product;
import com.example.Store.Models.VideoGame;
import com.example.Store.Repo.ProductRepository;
import com.example.Store.Repo.VideoGameRepository;
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

	@Bean
	CommandLineRunner initVideoGameDatabase(VideoGameRepository videoGameRepository) {
		return args -> {
			videoGameRepository.save(VideoGame.videoGameBuilder()
					.name("Red Dead Redemption 2")
					.description("Epic Western action-adventure game by Rockstar Games")
					.price(59.99)
					.developerName("Rockstar Games")
					.minimumAge(18)
					.category(Category.Action)
					.dateAdded(LocalDateTime.now())
					.lastModified(LocalDateTime.now())
					.build());

			videoGameRepository.save(VideoGame.videoGameBuilder()
					.name("Ghost of Tsushima")
					.description("Open-world samurai action game")
					.price(49.99)
					.developerName("Sucker Punch Productions")
					.minimumAge(17)
					.category(Category.Action)
					.dateAdded(LocalDateTime.now())
					.lastModified(LocalDateTime.now())
					.build());

			videoGameRepository.save(VideoGame.videoGameBuilder()
					.name("Overwatch")
					.description("Team-based multiplayer shooter by Blizzard Entertainment")
					.price(39.99)
					.developerName("Blizzard Entertainment")
					.minimumAge(13)
					.category(Category.Strategy)
					.dateAdded(LocalDateTime.now())
					.lastModified(LocalDateTime.now())
					.build());
		};
	}
}
