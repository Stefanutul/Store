package com.example.Store.Mapper;


import com.example.Store.DTO.ProductRequestDTO;
import com.example.Store.DTO.ProductResponseDTO;
import com.example.Store.Models.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }




    public ProductResponseDTO toDto(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
