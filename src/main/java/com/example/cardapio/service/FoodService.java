package com.example.cardapio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cardapio.exception.FoodNotFoundException;
import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;

@Service
public class FoodService {

    @Autowired
    private FoodRepository repository;

    public List<FoodResponseDTO> getAllFoods() {
        return repository.findAll().stream()
                .map(FoodResponseDTO::new)
                .toList();
    }

    public FoodResponseDTO getFoodById(Long id) {
        Food food = repository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));
        return new FoodResponseDTO(food);
    }

    public FoodResponseDTO createFood(FoodRequestDTO data) {
        Food food = new Food(data);
        Food savedFood = repository.save(food);
        return new FoodResponseDTO(savedFood);
    }

    public FoodResponseDTO updateFood(Long id, FoodRequestDTO data) {
        Food existingFood = repository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));
        
        Food updatedFood = new Food(existingFood.getId(), data.title(), data.image(), data.price());
        Food savedFood = repository.save(updatedFood);
        return new FoodResponseDTO(savedFood);
    }

    public void deleteFood(Long id) {
        Food food = repository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));
        repository.delete(food);
    }
}
