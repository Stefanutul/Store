package com.example.Store.DTO;

import com.example.Store.Enums.Category;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class VideoGameRequestDTO extends ProductRequestDTO {
    private String developerName;
    private int minimumAge;
    private Category category;
}
