package com.example.Store.Repo;

import com.example.Store.Models.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCardRepository extends JpaRepository<CustomerCard, Long> {
}
