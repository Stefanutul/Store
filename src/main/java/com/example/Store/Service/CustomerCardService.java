package com.example.Store.Service;

import com.example.Store.DTO.CustomerCardRequestDTO;
import com.example.Store.DTO.CustomerCardResponseDTO;

import java.util.List;

public interface CustomerCardService {
    CustomerCardResponseDTO addCustomerCard(CustomerCardRequestDTO dto);

    CustomerCardResponseDTO findCustomerCardById(Long id);

    List<CustomerCardResponseDTO> listAllCustomerCards();

    CustomerCardResponseDTO deleteCustomerCardById(Long id);

    CustomerCardResponseDTO updateBalance(Long id, Double newBalance);
}
