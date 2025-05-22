package com.example.Store.Models;

import jakarta.persistence.Entity;
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
    private String category;

    @Builder(builderMethodName = "videoGameBuilder")
    public VideoGame(Long id, String name, String description, double price, int quantity,
                     LocalDateTime dateAdded, LocalDateTime lastModified,
                     String developerName, int minimumAge, String category) {
        super(id, name, description, price, quantity, dateAdded, lastModified);
        this.developerName = developerName;
        this.minimumAge = minimumAge;
        this.category = category;
    }
}
