package com.example.Store.Service;

import com.example.Store.DTO.VideoGameRequestDTO;
import com.example.Store.DTO.VideoGameResponseDTO;
import com.example.Store.Enums.Category;

import java.util.List;

public interface VideoGameService {
    VideoGameResponseDTO addVideoGame(VideoGameRequestDTO dto);

    VideoGameResponseDTO findVideoGameById(Long id);

    List<VideoGameResponseDTO> findByMinimumAge(int minAge);

    List<VideoGameResponseDTO> findByCategory(Category category);

    VideoGameResponseDTO changeVideoGamePrice(Long id, double newPrice);

    List<VideoGameResponseDTO> listAllVideoGames();

    VideoGameResponseDTO deleteVideoGameById(Long id);

    List<VideoGameResponseDTO> findGamesSuitableForMinors();
}
