package com.example.Store.DTO;

public record CustomerCardRequestDTO(
        String firstName,
        String lastName,
        Double balance,
        Integer age
) {}

