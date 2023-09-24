package com.fusionhub.reciperealm.webservices.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.dto.RecipeDto;
import com.fusionhub.reciperealm.webservices.mapping.RecipeMapper;
import com.fusionhub.reciperealm.webservices.repository.RecipeRepository;
import com.fusionhub.reciperealm.webservices.services.RecipeService;
import com.fusionhub.reciperealm.webservices.validation.RecipeValidation;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired 
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private RecipeValidation recipeValidation;
    @Override
    public RecipeDto save(RecipeDto recipe) { 
        recipeValidation.validateRecipe(recipe);
        return recipeMapper.convertToRecipeDto(recipeRepository.save(recipeMapper.convertToRecipe(recipe)));
    }

    @Override
    public Optional<RecipeDto> findById(Long id) {
        return Optional.of(recipeMapper.convertToRecipeDto(recipeRepository.findById(id).get()));
    }

    @Override
    public List<RecipeDto> findAll() {
        return recipeMapper.convertToRecipeDtoList(recipeRepository.findAll());
    }

    @Override
    public List<RecipeDto> findByName(String name) {
        return recipeMapper.convertToRecipeDtoList(recipeRepository.findByName(name));
    }

    @Override
    public List<RecipeDto> findByIngredientName(String ingredientName) {
        return recipeMapper.convertToRecipeDtoList(recipeRepository.findByDetails_Ingredients_Name(ingredientName));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
    
}
