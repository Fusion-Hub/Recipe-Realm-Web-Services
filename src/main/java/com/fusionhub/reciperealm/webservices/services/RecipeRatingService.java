package com.fusionhub.reciperealm.webservices.services;

import java.util.List;
import java.util.Optional;

import com.fusionhub.reciperealm.webservices.dto.RecipeRatingDto;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.User;

public interface RecipeRatingService {
    public abstract  RecipeRatingDto rateRecipe(User user, Recipe recipe, RecipeRatingDto ratingDto);

    public abstract List<RecipeRatingDto> getRatingsForRecipe(Recipe recipe);

    public abstract Optional<Double> getAverageRatingForRecipe(Recipe recipe);

    public abstract void deleteRating(User user, Recipe recipe);
}
