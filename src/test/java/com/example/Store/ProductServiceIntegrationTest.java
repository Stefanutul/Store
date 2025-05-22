package com.example.Store;

import com.example.Store.DTO.ProductRequestDTO;
import com.example.Store.DTO.ProductResponseDTO;
import com.example.Store.Models.Product;
import com.example.Store.Repo.ProductRepository;
import com.example.Store.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    private Long productId;

    @BeforeEach
    void setup() {
        productRepository.deleteAll();

        ProductRequestDTO dto = ProductRequestDTO.builder()
                .name("Test Product")
                .description("For price update test")
                .price(10.0)
                .build();

        ProductResponseDTO saved = productService.addProduct(dto);
        productId = saved.getId();
    }

    @Test
    void testChangePriceUpdatesProductAndLastModified() throws InterruptedException {
        Product original = productRepository.findById(productId).orElseThrow();
        LocalDateTime originalLastModified = original.getLastModified();

        Thread.sleep(10);

        double newPrice = 15.0;

        ProductResponseDTO updated = productService.changePrice(productId, newPrice);
        assertThat(updated.getPrice()).isEqualTo(newPrice);
        Product productFromDb = productRepository.findById(productId).orElseThrow();
        assertThat(productFromDb.getLastModified()).isAfterOrEqualTo(originalLastModified);
    }
}
