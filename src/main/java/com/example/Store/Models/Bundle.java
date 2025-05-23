package com.example.Store.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bundle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bundle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;
    private Double bundlePrice;

    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;
    private LocalDateTime expiryDate;

    @ManyToMany
    @JoinTable(
            name = "bundle_products",
            joinColumns = @JoinColumn(name = "bundle_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDateTime.now();
        lastUpdated = LocalDateTime.now();
        expiryDate = dateCreated.plusWeeks(1);
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}
