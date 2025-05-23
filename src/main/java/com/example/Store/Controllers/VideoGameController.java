package com.example.Store.Controllers;

import com.example.Store.DTO.PriceUpdateRequestDTO;
import com.example.Store.DTO.VideoGameRequestDTO;
import com.example.Store.DTO.VideoGameResponseDTO;
import com.example.Store.Enums.Category;
import com.example.Store.Service.VideoGameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videogames")
@RequiredArgsConstructor
public class VideoGameController {

    private final VideoGameService videoGameService;

    @PostMapping("/add")
    public ResponseEntity<VideoGameResponseDTO> addVideoGame(@Valid @RequestBody VideoGameRequestDTO dto) {
        VideoGameResponseDTO saved = videoGameService.addVideoGame(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoGameResponseDTO> getVideoGameById(@PathVariable Long id) {
        VideoGameResponseDTO videoGame = videoGameService.findVideoGameById(id);
        return ResponseEntity.ok(videoGame);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VideoGameResponseDTO>> getAllVideoGames() {
        return ResponseEntity.ok(videoGameService.listAllVideoGames());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<VideoGameResponseDTO> deleteVideoGame(@PathVariable Long id) {
        VideoGameResponseDTO deleted = videoGameService.deleteVideoGameById(id);
        return ResponseEntity.ok(deleted);
    }

    @PutMapping("/update/price/{id}")
    public ResponseEntity<VideoGameResponseDTO> updatePrice(@PathVariable Long id,
                                                            @RequestBody PriceUpdateRequestDTO request) {
        VideoGameResponseDTO updated = videoGameService.changeVideoGamePrice(id, request.getPrice());
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<VideoGameResponseDTO>> getVideoGamesByCategory(@PathVariable Category category) {
        List<VideoGameResponseDTO> games = videoGameService.findByCategory(category);
        return ResponseEntity.ok(games);
    }

        @GetMapping("/suitable-for-minors")
    public ResponseEntity<List<VideoGameResponseDTO>> getGamesSuitableForMinors() {
        List<VideoGameResponseDTO> games = videoGameService.findGamesSuitableForMinors();
        return ResponseEntity.ok(games);
    }
}
