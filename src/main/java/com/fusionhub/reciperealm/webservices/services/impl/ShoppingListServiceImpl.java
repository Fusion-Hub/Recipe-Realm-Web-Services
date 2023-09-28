package com.fusionhub.reciperealm.webservices.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.dto.ShoppingListDto;
import com.fusionhub.reciperealm.webservices.mapping.ShoppingListMapper;
import com.fusionhub.reciperealm.webservices.models.Ingredients;
import com.fusionhub.reciperealm.webservices.models.ShoppingList;
import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.repository.IngredientsRepository;
import com.fusionhub.reciperealm.webservices.repository.ShoppingListRepository;
import com.fusionhub.reciperealm.webservices.repository.UserRepository;
import com.fusionhub.reciperealm.webservices.services.ShoppingListService;
import com.fusionhub.reciperealm.webservices.validation.ShoppingListValidation;

@Service
public class ShoppingListServiceImpl implements ShoppingListService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private ShoppingListMapper shoppingListMapper;

    @Autowired
    private ShoppingListValidation shoppingListValidation;

    @Override
public List<ShoppingListDto> getShoppingListByUserId(Long userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
    List<ShoppingList> items = shoppingListRepository.findByUser(user);
    return items.stream()
        .map(shoppingListMapper::convertToDto)
        .collect(Collectors.toList());
}

    @Override
    public void addIngredientToShoppingList(Long userId, Long ingredientId) {
        shoppingListValidation.validateAddition(userId, ingredientId);

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        Ingredients ingredient = ingredientsRepository.findById(ingredientId).orElseThrow(() -> new RuntimeException("Ingredient not found!"));

        ShoppingList item = new ShoppingList();
        item.setUser(user);
        item.setIngredient(ingredient);
        shoppingListRepository.save(item);
    }

    @Override
    public void removeIngredientFromShoppingList(Long userId, Long ingredientId) {
        shoppingListValidation.validateRemoval(userId, ingredientId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        Ingredients ingredient = ingredientsRepository.findById(ingredientId).orElseThrow(() -> new RuntimeException("Ingredient not found!"));

        ShoppingList item = shoppingListRepository.findByUserAndIngredient(user, ingredient).get();
        shoppingListRepository.delete(item);
    }

    @Override
    public boolean isIngredientInShoppingList(Long userId, Long ingredientId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        Ingredients ingredient = ingredientsRepository.findById(ingredientId).orElseThrow(() -> new RuntimeException("Ingredient not found!"));

        return shoppingListRepository.findByUserAndIngredient(user, ingredient).isPresent();
    }
}
