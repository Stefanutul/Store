package com.example.Store.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class VideoGameResponseDTO extends ProductResponseDTO {
    private String developerName;
    private int minimumAge;
    private String category;
}
