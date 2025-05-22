package com.example.Store.Models;

import com.example.Store.Enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoGame extends Product {

    private String developerName;
    private int minimumAge;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder(builderMethodName = "videoGameBuilder")
    public VideoGame(Long id, String name, String description, double price, int quantity,
                     LocalDateTime dateAdded, LocalDateTime lastModified,
                     String developerName, int minimumAge, Category category) {
        super(id, name, description, price, quantity, dateAdded, lastModified);
        this.developerName = developerName;
        this.minimumAge = minimumAge;
        this.category = category;
    }
}
