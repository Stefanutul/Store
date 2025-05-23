package com.example.Store;

import com.example.Store.DTO.ProductRequestDTO;
import com.example.Store.DTO.ProductResponseDTO;
import com.example.Store.Exceptions.ProductNotFoundException;
import com.example.Store.Mapper.ProductMapper;
import com.example.Store.Models.Product;
import com.example.Store.Repo.ProductRepository;
import com.example.Store.Service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductRequestDTO requestDTO;
    private ProductResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = Product.builder()
                .id(1L)
                .name("Test Product")
                .description("Test Description")
                .price(100.0)
                .build();

        requestDTO = ProductRequestDTO.builder()
                .name("Test Product")
                .description("Test Description")
                .price(100.0)
                .build();

        responseDTO = ProductResponseDTO.builder()
                .id(1L)
                .name("Test Product")
                .description("Test Description")
                .price(100.0)
                .build();
    }

    @Test
    void addProduct_ShouldReturnSavedProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDto(any())).thenReturn(responseDTO);

        ProductResponseDTO result = productService.addProduct(requestDTO);

        assertThat(result).isEqualTo(responseDTO);
    }

    @Test
    void findProductById_ShouldReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(responseDTO);

        ProductResponseDTO result = productService.findProductById(1L);

        assertThat(result).isEqualTo(responseDTO);
    }

    @Test
    void findProductById_NotFound_ShouldThrowException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.findProductById(1L))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    void changePrice_ShouldUpdateAndReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(responseDTO);

        ProductResponseDTO result = productService.changePrice(1L, 150.0);

        assertThat(result).isEqualTo(responseDTO);
        assertThat(product.getPrice()).isEqualTo(150.0);
    }

    @Test
    void listAllProducts_ShouldReturnProductList() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productMapper.toDto(product)).thenReturn(responseDTO);

        List<ProductResponseDTO> results = productService.listAllProducts();

        assertThat(results).hasSize(1);
        assertThat(results.get(0)).isEqualTo(responseDTO);
    }

    @Test
    void deleteProductById_ShouldDeleteAndReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(responseDTO);

        ProductResponseDTO result = productService.deleteProductById(1L);

        verify(productRepository).delete(product);
        assertThat(result).isEqualTo(responseDTO);
    }

    @Test
    void getNameById_ShouldReturnProductName() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        String result = productService.getNameById(1L);

        assertThat(result).isEqualTo("Test Product");
    }

    @Test
    void getNameById_NotFound_ShouldThrowException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.getNameById(1L))
                .isInstanceOf(ProductNotFoundException.class);
    }
}
