package com.example.Store.Controllers;

import com.example.Store.DTO.VideoGameRequestDTO;
import com.example.Store.DTO.VideoGameResponseDTO;
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

    // Add a new video game
    @PostMapping("/add")
    public ResponseEntity<VideoGameResponseDTO> addVideoGame(@Valid @RequestBody VideoGameRequestDTO dto) {
        VideoGameResponseDTO saved = videoGameService.addVideoGame(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Get video game by ID
    @GetMapping("/{id}")
    public ResponseEntity<VideoGameResponseDTO> getVideoGameById(@PathVariable Long id) {
        VideoGameResponseDTO videoGame = videoGameService.findVideoGameById(id);
        return ResponseEntity.ok(videoGame);
    }

}
