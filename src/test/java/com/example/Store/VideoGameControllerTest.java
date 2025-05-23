package com.example.Store;

import com.example.Store.Controllers.VideoGameController;
import com.example.Store.Models.CustomerCard;
import com.example.Store.DTO.PriceUpdateRequestDTO;
import com.example.Store.DTO.VideoGameRequestDTO;
import com.example.Store.DTO.VideoGameResponseDTO;
import com.example.Store.Enums.Category;
import com.example.Store.Service.VideoGameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VideoGameControllerTest {

    @InjectMocks
    private VideoGameController videoGameController;

    @Mock
    private VideoGameService videoGameService;

    private VideoGameRequestDTO requestDTO;
    private VideoGameResponseDTO responseDTO;
    private PriceUpdateRequestDTO priceUpdateDTO;
    private CustomerCard customerCard;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        requestDTO = VideoGameRequestDTO.builder()
                .name("Test Game")
                .description("Test Description")
                .price(20.0)
                .developerName("Test Dev")
                .minimumAge(12)
                .category(Category.Action)
                .build();

        responseDTO = VideoGameResponseDTO.builder()
                .id(1L)
                .name("Test Game")
                .description("Test Description")
                .price(20.0)
                .developerName("Test Dev")
                .minimumAge(12)
                .category(Category.Action)
                .build();

        priceUpdateDTO = new PriceUpdateRequestDTO();
        priceUpdateDTO.setPrice(30.0);

        customerCard = CustomerCard.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .balance(100.0)
                .age(25)
                .build();
    }

    @Test
    void testAddVideoGame() {
        when(videoGameService.addVideoGame(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<VideoGameResponseDTO> response = videoGameController.addVideoGame(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(videoGameService, times(1)).addVideoGame(requestDTO);
    }

    @Test
    void testGetVideoGameById() {
        when(videoGameService.findVideoGameById(1L)).thenReturn(responseDTO);

        ResponseEntity<VideoGameResponseDTO> response = videoGameController.getVideoGameById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(videoGameService, times(1)).findVideoGameById(1L);
    }

    @Test
    void testGetAllVideoGames() {
        when(videoGameService.listAllVideoGames()).thenReturn(List.of(responseDTO));

        ResponseEntity<List<VideoGameResponseDTO>> response = videoGameController.getAllVideoGames();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(videoGameService, times(1)).listAllVideoGames();
    }

    @Test
    void testDeleteVideoGame() {
        when(videoGameService.deleteVideoGameById(1L)).thenReturn(responseDTO);

        ResponseEntity<VideoGameResponseDTO> response = videoGameController.deleteVideoGame(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(videoGameService, times(1)).deleteVideoGameById(1L);
    }

    @Test
    void testUpdatePrice() {
        when(videoGameService.changeVideoGamePrice(1L, 30.0)).thenReturn(responseDTO);

        ResponseEntity<VideoGameResponseDTO> response = videoGameController.updatePrice(1L, priceUpdateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(videoGameService, times(1)).changeVideoGamePrice(1L, 30.0);
    }

    @Test
    void testGetVideoGamesByCategory() {
        when(videoGameService.findByCategory(Category.Action)).thenReturn(List.of(responseDTO));

        ResponseEntity<List<VideoGameResponseDTO>> response = videoGameController.getVideoGamesByCategory(Category.Action);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(videoGameService, times(1)).findByCategory(Category.Action);
    }

    @Test
    void testGetGamesSuitableForMinors() {
        when(videoGameService.findGamesSuitableForMinors()).thenReturn(List.of(responseDTO));

        ResponseEntity<List<VideoGameResponseDTO>> response = videoGameController.getGamesSuitableForMinors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(videoGameService, times(1)).findGamesSuitableForMinors();
    }

    @Test
    void testPurchaseVideoGame() {
        when(videoGameService.purchaseVideoGame(1L, customerCard)).thenReturn(responseDTO);

        ResponseEntity<VideoGameResponseDTO> response = videoGameController.purchaseVideoGame(1L, customerCard);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(videoGameService, times(1)).purchaseVideoGame(1L, customerCard);
    }
}
