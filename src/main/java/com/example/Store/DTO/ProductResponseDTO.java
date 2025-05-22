package com.example.Store.DTO;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private LocalDateTime dateAdded;
    private LocalDateTime lastModified;
}
