package com.fusionhub.reciperealm.webservices.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.exception.ValidationException;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.repository.FavoriteRepository;
import com.fusionhub.reciperealm.webservices.repository.RecipeRepository;
import com.fusionhub.reciperealm.webservices.repository.UserRepository;

@Component
public class FavoriteValidation {
    
        @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public void validateFavorite(Long userId, Long recipeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ValidationException("User not found!"));
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ValidationException("Recipe not found!"));

        if (favoriteRepository.findByUserAndRecipe(user, recipe).isPresent()) {
            throw new ValidationException("User has already favorited this recipe");
        }
    }
    
}
