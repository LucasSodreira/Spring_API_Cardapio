package com.example.cardapio.exception;

public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException(Long id) {
        super("Prato não encontrado com o ID: " + id);
    }
}
