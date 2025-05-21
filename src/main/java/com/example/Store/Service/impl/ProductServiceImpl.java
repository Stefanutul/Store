package com.example.Store.Service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.Store.DTO.ProductRequestDTO;
import com.example.Store.DTO.ProductResponseDTO;
import com.example.Store.Mapper.ProductMapper;
import com.example.Store.Models.Product;
import com.example.Store.Repo.ProductRepository;
import com.example.Store.Service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO dto) {
        logger.info("Adding new product with title: {}", dto.getName());
        Product product = ProductMapper.toEntity(dto);
        Product saved = productRepository.save(product);
        logger.info("{} was added successfully with id: {}", saved.getName() , saved.getId() );
        return productMapper.toDto(saved);
    }

    @Override
    public ProductResponseDTO findProductById(Long id) {
        logger.info("Finding product by id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Product not found with id: {}", id);
                    return new RuntimeException("Product not found with id " + id);
                });
        logger.info("Product found: {}", product.getName());
        return productMapper.toDto(product);
    }
    @Transactional
    @Override
    public ProductResponseDTO changePrice(Long id, double newPrice) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Product not found with id: {}", id);
                    return new RuntimeException("Product not found with id " + id);
                });

        logger.info("Changing price for '{}' (id: {}) from {} to {}", product.getName(), id, product.getPrice(), newPrice);

        product.setPrice(newPrice);
        Product updated = productRepository.save(product);

        logger.info("Price updated for '{}' (id: {}) to {}", product.getName(), id, newPrice);

        return productMapper.toDto(updated);
    }

    @Override
    public List<ProductResponseDTO> listAllProducts() {
        logger.info("Listing all products");
        List<ProductResponseDTO> products = productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Total products found: {}", products.size());
        return products;
    }

    @Transactional
    @Override
    public ProductResponseDTO deleteProductById(Long id) {
        logger.info("Deleting product by id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Product not found with id: {}", id);
                    return new RuntimeException("Product not found with id " + id);
                });

        String productName = product.getName();  // get name before deleting
        productRepository.delete(product);
        logger.info("{} was deleted with id: {}", productName, id);

        return productMapper.toDto(product);
    }

    @Override
    public String getNameById(Long id) {
        logger.info("Getting title for product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Product not found with id: {}", id);
                    return new RuntimeException("Product not found with id" + id);
                });
        logger.info("Found product title: {}", product.getName());
        return product.getName();
    }
}
