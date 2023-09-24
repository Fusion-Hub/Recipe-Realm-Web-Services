package com.fusionhub.reciperealm.webservices.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.exception.ValidationException;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.repository.RecipeRatingRepository;

@Component
public class RecipeRatingValidation {

    @Autowired
    private RecipeRatingRepository recipeRatingRepository;

    public void validateRating(User user, Recipe recipe) {
        if (recipeRatingRepository.existsByUserAndRecipe(user, recipe)) {
            throw new ValidationException("The user has already rated this recipe.");
        }
    }
}