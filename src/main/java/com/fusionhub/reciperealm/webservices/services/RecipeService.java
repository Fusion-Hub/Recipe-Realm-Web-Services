package com.fusionhub.reciperealm.webservices.services;

import java.util.List;
import java.util.Optional;

import com.fusionhub.reciperealm.webservices.dto.RecipeDto;

public interface RecipeService {
    
    RecipeDto save(RecipeDto recipe);

    Optional<RecipeDto> findById(Long id);

    List<RecipeDto> findAll();

    List<RecipeDto> findByName(String name);

    List<RecipeDto> findByIngredientName(String ingredientName);

    void deleteById(Long id);



}
