package com.example.cardapio.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.cardapio.exception.FoodNotFoundException;
import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    @Mock
    private FoodRepository repository;

    @InjectMocks
    private FoodService foodService;

    private Food food;
    private FoodRequestDTO requestDTO;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        food = new Food(1L, "Pizza", "pizza.jpg", 2500);
        requestDTO = new FoodRequestDTO("Pizza", "pizza.jpg", 2500);
    }

    @Test
    void getAllFoods_ShouldReturnListOfFoodResponseDTO() {
        // Given
        when(repository.findAll()).thenReturn(List.of(food));

        // When
        List<FoodResponseDTO> result = foodService.getAllFoods();

        // Then
        assertEquals(1, result.size());
        assertEquals("Pizza", result.get(0).title());
        verify(repository).findAll();
    }

    @Test
    void getFoodById_WhenFoodExists_ShouldReturnFoodResponseDTO() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(food));

        // When
        FoodResponseDTO result = foodService.getFoodById(1L);

        // Then
        assertEquals("Pizza", result.title());
        assertEquals(2500, result.price());
        verify(repository).findById(1L);
    }

    @Test
    void getFoodById_WhenFoodNotExists_ShouldThrowException() {
        // Given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        FoodNotFoundException exception = assertThrows(FoodNotFoundException.class, () -> foodService.getFoodById(1L));
        assertEquals("Prato não encontrado com o ID: 1", exception.getMessage());
        verify(repository).findById(1L);
    }

    @Test
    void createFood_ShouldReturnFoodResponseDTO() {
        // Given
        when(repository.save(any(Food.class))).thenReturn(food);

        // When
        FoodResponseDTO result = foodService.createFood(requestDTO);

        // Then
        assertEquals("Pizza", result.title());
        assertEquals(2500, result.price());
        verify(repository).save(any(Food.class));
    }

    @Test
    void deleteFood_WhenFoodExists_ShouldDeleteFood() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(food));

        // When
        foodService.deleteFood(1L);

        // Then
        verify(repository).findById(1L);
        verify(repository).delete(food);
    }

    @Test
    void deleteFood_WhenFoodNotExists_ShouldThrowException() {
        // Given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        FoodNotFoundException exception = assertThrows(FoodNotFoundException.class, () -> foodService.deleteFood(1L));
        assertEquals("Prato não encontrado com o ID: 1", exception.getMessage());
        verify(repository).findById(1L);
        verify(repository, never()).delete(any());
    }
}
