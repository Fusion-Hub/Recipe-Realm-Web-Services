package com.fusionhub.reciperealm.webservices.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.models.Ingredients;
import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.repository.IngredientsRepository;
import com.fusionhub.reciperealm.webservices.repository.ShoppingListRepository;
import com.fusionhub.reciperealm.webservices.repository.UserRepository;

@Component
public class ShoppingListValidation {
    
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    public void validateAddition(Long userId, Long ingredientId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        Ingredients ingredient = ingredientsRepository.findById(ingredientId).orElseThrow(() -> new RuntimeException("Ingredient not found!"));

        boolean exists = shoppingListRepository.findByUserAndIngredient(user, ingredient).isPresent();
        if (exists) {
            throw new IllegalArgumentException("Ingredient already exists in the user's shopping list.");
        }
    }

    public void validateRemoval(Long userId, Long ingredientId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        Ingredients ingredient = ingredientsRepository.findById(ingredientId).orElseThrow(() -> new RuntimeException("Ingredient not found!"));

        boolean exists = shoppingListRepository.findByUserAndIngredient(user, ingredient).isPresent();
        if (!exists) {
            throw new IllegalArgumentException("Ingredient doesn't exist in the user's shopping list.");
        }
    }
}
