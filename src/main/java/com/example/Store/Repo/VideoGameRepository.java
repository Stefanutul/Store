package com.example.Store.Repo;

import com.example.Store.Models.VideoGame;
import com.example.Store.Enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {

    List<VideoGame> findByMinimumAgeGreaterThanEqual(int minAge);

    List<VideoGame> findByCategory(Category category);
}
