package com.example.Store.Mapper;

import com.example.Store.DTO.VideoGameRequestDTO;
import com.example.Store.DTO.VideoGameResponseDTO;
import com.example.Store.Models.VideoGame;

public class VideoGameMapper {

    public static VideoGame toEntity(VideoGameRequestDTO dto) {
        return VideoGame.videoGameBuilder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .developerName(dto.getDeveloperName())
                .minimumAge(dto.getMinimumAge())
                .category(dto.getCategory())
                .build();
    }

    public static VideoGameResponseDTO toDto(VideoGame game) {
        return VideoGameResponseDTO.builder()
                .name(game.getName())
                .description(game.getDescription())
                .price(game.getPrice())
                .dateAdded(game.getDateAdded())
                .lastModified(game.getLastModified())
                .developerName(game.getDeveloperName())
                .minimumAge(game.getMinimumAge())
                .category(game.getCategory())
                .build();
    }
}
