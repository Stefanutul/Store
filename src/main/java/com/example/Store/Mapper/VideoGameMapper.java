package com.example.Store.Mapper;

import com.example.Store.DTO.VideoGameRequestDTO;
import com.example.Store.DTO.VideoGameResponseDTO;
import com.example.Store.Models.VideoGame;

public class VideoGameMapper {

    public static VideoGameResponseDTO toDto(VideoGame videoGame) {
        if (videoGame == null) {
            return null;
        }

        return VideoGameResponseDTO.builder()
                .id(videoGame.getId())
                .name(videoGame.getName())
                .description(videoGame.getDescription())
                .price(videoGame.getPrice())
                .dateAdded(videoGame.getDateAdded())
                .lastModified(videoGame.getLastModified())
                .developerName(videoGame.getDeveloperName())
                .minimumAge(videoGame.getMinimumAge())
                .category(videoGame.getCategory())
                .build();
    }

    public static VideoGame toEntity(VideoGameRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return VideoGame.videoGameBuilder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .developerName(dto.getDeveloperName())
                .minimumAge(dto.getMinimumAge())
                .category(dto.getCategory())
                .build();
    }
}
