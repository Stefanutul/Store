package com.example.Store.Service.impl;

import com.example.Store.DTO.VideoGameRequestDTO;
import com.example.Store.DTO.VideoGameResponseDTO;
import com.example.Store.Enums.Category;
import com.example.Store.Models.VideoGame;
import com.example.Store.Repo.VideoGameRepository;
import com.example.Store.Exceptions.VideoGameNotFoundException;
import com.example.Store.Mapper.VideoGameMapper;
import com.example.Store.Service.VideoGameService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoGameServiceImpl implements VideoGameService {

    private static final Logger logger = LoggerFactory.getLogger(VideoGameServiceImpl.class);

    private final VideoGameRepository videoGameRepository;

    @Override
    @Transactional
    public VideoGameResponseDTO addVideoGame(VideoGameRequestDTO dto) {
        logger.info("Adding new video game: {}", dto.getName());
        VideoGame videoGame = VideoGameMapper.toEntity(dto);
        VideoGame saved = videoGameRepository.save(videoGame);
        logger.info("Video game added successfully with id: {}", saved.getId());
        return VideoGameMapper.toDto(saved);
    }

    @Override
    public VideoGameResponseDTO findVideoGameById(Long id) {
        logger.info("Finding video game by id: {}", id);
        VideoGame videoGame = videoGameRepository.findById(id)
                .orElseThrow(() -> new VideoGameNotFoundException(id));
        logger.info("Video game found: {}", videoGame.getName());
        return VideoGameMapper.toDto(videoGame);
    }

    @Override
    public List<VideoGameResponseDTO> findByMinimumAge(int minAge) {
        logger.info("Searching video games with minimum age >= {}", minAge);
        List<VideoGame> games = videoGameRepository.findByMinimumAgeGreaterThanEqual(minAge);
        return games.stream()
                .map(VideoGameMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VideoGameResponseDTO> findByCategory(Category category) {
        logger.info("Searching video games with category: {}", category);
        List<VideoGame> games = videoGameRepository.findByCategory(category);
        return games.stream()
                .map(VideoGameMapper::toDto)
                .collect(Collectors.toList());
    }
}
