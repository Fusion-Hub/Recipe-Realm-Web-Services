package com.fusionhub.reciperealm.webservices.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.UserIngredientsDto;
import com.fusionhub.reciperealm.webservices.exception.ValidationException;
import com.fusionhub.reciperealm.webservices.models.Ingredients;
import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.repository.IngredientsRepository;
import com.fusionhub.reciperealm.webservices.repository.UserIngredientsRepository;
import com.fusionhub.reciperealm.webservices.repository.UserRepository;

@Component
public class UserIngredientsValidation {
    
    @Autowired
    private UserIngredientsRepository userIngredientsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    public void validateAddition(UserIngredientsDto request) {
        if (request.getUserId() == null) {
            throw new ValidationException("User ID is required");
        }
        if (request.getIngredientId() == null) {
            throw new ValidationException("Ingredient ID is required");
        }
        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new ValidationException("Valid ingredient quantity is required");
        }
        if (request.getUnit() == null) {
            throw new ValidationException("Measurement unit is required");
        }

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found!"));
        Ingredients ingredient = ingredientsRepository.findById(request.getIngredientId()).orElseThrow(() -> new RuntimeException("Ingredient not found!"));

        boolean exists = userIngredientsRepository.findByUser_IdAndIngredient_Id(user.getId(), ingredient.getId()).isPresent();
        if (exists) {
            throw new ValidationException("Ingredient already exists in the user's pantry");
        }
    }

    public void validateUpdate(UserIngredientsDto request) {
        if (request.getUserId() == null || request.getIngredientId() == null) {
            throw new ValidationException("Both user ID and ingredient ID are required for update");
        }

        boolean exists = userIngredientsRepository.findByUser_IdAndIngredient_Id(request.getUserId(), request.getIngredientId()).isPresent();
        if (!exists) {
            throw new ValidationException("Ingredient not found in user's pantry for update");
        }
    }

    public void validateRemoval(Long userId, Long ingredientId) {
        boolean exists = userIngredientsRepository.findByUser_IdAndIngredient_Id(userId, ingredientId).isPresent();
        if (!exists) {
            throw new ValidationException("Ingredient doesn't exist in the user's pantry");
        }
    }
}