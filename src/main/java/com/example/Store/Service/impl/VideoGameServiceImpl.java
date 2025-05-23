package com.example.Store.Service.impl;

import com.example.Store.DTO.VideoGameRequestDTO;
import com.example.Store.DTO.VideoGameResponseDTO;
import com.example.Store.Enums.Category;
import com.example.Store.Exceptions.InsufficientBalanceException;
import com.example.Store.Exceptions.UnderagePurchaseException;
import com.example.Store.Models.CustomerCard;
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

    @Override
    public VideoGameResponseDTO changeVideoGamePrice(Long id, double newPrice) {
        logger.info("Updating video game price for id: {}", id);
        VideoGame videoGame = videoGameRepository.findById(id)
                .orElseThrow(() -> new VideoGameNotFoundException(id));
        logger.info("Old price: {}, New price: {}", videoGame.getPrice(), newPrice);
        videoGame.setPrice(newPrice);
        VideoGame updated = videoGameRepository.save(videoGame);
        return VideoGameMapper.toDto(updated);
    }

    @Override
    public List<VideoGameResponseDTO> listAllVideoGames() {
        logger.info("Listing all video games");
        return videoGameRepository.findAll().stream()
                .map(VideoGameMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VideoGameResponseDTO deleteVideoGameById(Long id) {
        logger.info("Deleting video game with ID: {}", id);
        VideoGame videoGame = videoGameRepository.findById(id)
                .orElseThrow(() -> new VideoGameNotFoundException(id));
        videoGameRepository.delete(videoGame);
        return VideoGameMapper.toDto(videoGame);
    }

    @Override
    public List<VideoGameResponseDTO> findGamesSuitableForMinors() {
        logger.info("Fetching all video games to filter those suitable for minors (minimum age < 16)");
        List<VideoGame> allGames = videoGameRepository.findAll();
        List<VideoGameResponseDTO> suitableGames = allGames.stream()
                .filter(game -> game.getMinimumAge() < 16)
                .map(VideoGameMapper::toDto)
                .collect(Collectors.toList());

        logger.info("Found {} suitable games", suitableGames.size());
        return suitableGames;
    }

    @Override
    @Transactional
    public VideoGameResponseDTO purchaseVideoGame(Long videoGameId, CustomerCard customerCard) {
        logger.info("Attempting to purchase video game with ID: {}", videoGameId);

        VideoGame videoGame = videoGameRepository.findById(videoGameId)
                .orElseThrow(() -> {
                    logger.error("Video game not found with ID: {}", videoGameId);
                    return new VideoGameNotFoundException(videoGameId);
                });

        if (customerCard.getAge() < videoGame.getMinimumAge()) {
            logger.warn("Purchase failed - age {} is below the required minimum age of {}", customerCard.getAge(), videoGame.getMinimumAge());
            throw new UnderagePurchaseException(videoGame.getMinimumAge());
        }

        if (customerCard.getBalance() < videoGame.getPrice()) {
            logger.warn("Purchase failed - insufficient balance. Required: {}, Available: {}", videoGame.getPrice(), customerCard.getBalance());
            throw new InsufficientBalanceException();
        }

        // Purchase successful: deduct balance and delete game
        logger.info("Purchase successful. Removing video game '{}' from the store", videoGame.getName());
        videoGameRepository.delete(videoGame);

        return VideoGameMapper.toDto(videoGame);
    }
}
