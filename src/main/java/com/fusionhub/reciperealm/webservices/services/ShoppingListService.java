package com.fusionhub.reciperealm.webservices.services;

import java.util.List;

import com.fusionhub.reciperealm.webservices.dto.ShoppingListDto;

public interface ShoppingListService {

    List<ShoppingListDto> getShoppingListByUserId(Long userId);
    void addIngredientToShoppingList(Long userId, Long ingredientId);
    void removeIngredientFromShoppingList(Long userId, Long ingredientId);
    boolean isIngredientInShoppingList(Long userId, Long ingredientId);
    
}
