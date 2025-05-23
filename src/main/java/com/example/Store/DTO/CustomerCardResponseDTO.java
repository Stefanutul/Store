package com.example.Store.DTO;

public record CustomerCardResponseDTO(
        Long id,
        String firstName,
        String lastName,
        Double balance,
        Integer age
) {}
