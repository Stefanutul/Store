package com.example.Store;

import com.example.Store.DTO.ProductResponseDTO;
import com.example.Store.Exceptions.ProductNotFoundException;
import com.example.Store.Mapper.ProductMapper;
import com.example.Store.Models.Product;
import com.example.Store.Repo.ProductRepository;
import com.example.Store.Service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    @DisplayName("findProductById: returns product when found")
    void findProductById_shouldReturnProduct_whenFound() {

        final Long productId = 1L;
        final Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setName("Test Product");

        final ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(productId);
        responseDTO.setName("Test Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));
        when(productMapper.toDto(mockProduct)).thenReturn(responseDTO);

        final ProductResponseDTO result = productService.findProductById(productId);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Test Product");
        verify(productRepository).findById(productId);
        verify(productMapper).toDto(mockProduct);
    }

    @Test
    @DisplayName("findProductById: throws ProductNotFoundException when product not found")
    void findProductById_shouldThrowException_whenNotFound() {
        final Long productId = 999L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.findProductById(productId))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product with id: 999 not found");

        verify(productRepository).findById(productId);
    }
}
