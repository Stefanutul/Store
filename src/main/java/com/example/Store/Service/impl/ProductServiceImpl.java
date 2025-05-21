package com.example.Store.Service.impl;

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

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        Product saved = productRepository.save(product);
        return productMapper.toDto(saved);
    }

    @Override
    public ProductResponseDTO findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        return productMapper.toDto(product);
    }

    @Override
    public ProductResponseDTO changePrice(Long id, double newPrice) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        product.setPrice(newPrice);
        Product updated = productRepository.save(product);
        return productMapper.toDto(updated);
    }

    @Override
    public List<ProductResponseDTO> listAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductResponseDTO deleteProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        productRepository.delete(product);
        return productMapper.toDto(product);
    }
}
