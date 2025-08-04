package com.example.cardapio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;
import com.example.cardapio.service.FoodService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("food")
public class FoodController {
    
    @Autowired
    private FoodService foodService;

    @PostMapping()
    public ResponseEntity<FoodResponseDTO> saveFood(@Valid @RequestBody FoodRequestDTO data){
        FoodResponseDTO savedFood = foodService.createFood(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFood);
    }

    @GetMapping
    public List<FoodResponseDTO> getAll(){
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDTO> getById(@PathVariable Long id){
        FoodResponseDTO food = foodService.getFoodById(id);
        return ResponseEntity.ok(food);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodResponseDTO> updateFood(@PathVariable Long id, @Valid @RequestBody FoodRequestDTO data){
        FoodResponseDTO updatedFood = foodService.updateFood(id, data);
        return ResponseEntity.ok(updatedFood);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id){
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }
}
