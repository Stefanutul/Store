package com.example.Store.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private Integer age;

}
