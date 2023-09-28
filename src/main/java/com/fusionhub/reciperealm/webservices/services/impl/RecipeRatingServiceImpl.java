package com.fusionhub.reciperealm.webservices.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.dto.RecipeRatingDto;
import com.fusionhub.reciperealm.webservices.mapping.RecipeRatingMapper;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.RecipeRating;
import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.repository.RecipeRatingRepository;
import com.fusionhub.reciperealm.webservices.services.RecipeRatingService;
import com.fusionhub.reciperealm.webservices.validation.RecipeRatingValidation;

@Service
public class RecipeRatingServiceImpl implements RecipeRatingService{
    
    @Autowired
    private RecipeRatingRepository recipeRatingRepository;

    @Autowired
    private RecipeRatingValidation recipeRatingValidation;

    @Autowired
    private RecipeRatingMapper recipeRatingMapper;

    @Override
    public RecipeRatingDto rateRecipe(User user, Recipe recipe, RecipeRatingDto ratingDto) {
        recipeRatingValidation.validateRating(user, recipe);

        RecipeRating recipeRating = recipeRatingMapper.convertToRecipeRating(ratingDto);
        recipeRating.setUser(user);
        recipeRating.setRecipe(recipe);

        recipeRatingRepository.save(recipeRating);

        return recipeRatingMapper.convertToRecipeRatingDto(recipeRating);
    }

    @Override
    public List<RecipeRatingDto> getRatingsForRecipe(Recipe recipe) {
        List<RecipeRating> ratings = recipeRatingRepository.findAllByRecipe(recipe);
        return recipeRatingMapper.convertToRecipeRatingDtoList(ratings);
    }
    
    @Override
    public Optional<Double> getAverageRatingForRecipe(Recipe recipe) {
        return recipeRatingRepository.findAverageRatingByRecipe(recipe);
    }

    @Override
    public void deleteRating(User user, Recipe recipe) {
        Optional<RecipeRating> existingRating = recipeRatingRepository.findByUserAndRecipe(user, recipe);
        existingRating.ifPresent(recipeRating -> recipeRatingRepository.delete(recipeRating));
    }
}
