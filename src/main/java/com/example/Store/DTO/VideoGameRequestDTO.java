package com.example.Store.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoGameRequestDTO extends ProductRequestDTO {
    private String developerName;
    private int minimumAge;
    private String category;
}
