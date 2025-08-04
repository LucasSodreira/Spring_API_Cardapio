package com.example.cardapio.food;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FoodRequestDTO(
    @NotBlank(message = "Título é obrigatório")
    String title,
    
    @NotBlank(message = "Imagem é obrigatória") 
    String image,
    
    @NotNull(message = "Preço é obrigatório")
    @Min(value = 1, message = "Preço deve ser maior que zero")
    Integer price
) {}
