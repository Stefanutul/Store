package com.example.Store.Service;

import com.example.Store.DTO.ProductRequestDTO;
import com.example.Store.DTO.ProductResponseDTO;


import java.util.List;


public interface ProductService {

    ProductResponseDTO addProduct(ProductRequestDTO dto);

    ProductResponseDTO findProductById(Long id);

    ProductResponseDTO changePrice(Long id, double newPrice);

    List<ProductResponseDTO> listAllProducts();

    ProductResponseDTO deleteProductById(Long id);
}
