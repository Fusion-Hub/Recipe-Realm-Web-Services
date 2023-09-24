package com.fusionhub.reciperealm.webservices.services;

import com.fusionhub.reciperealm.webservices.dto.UserIngredientsDto;

public interface UserIngredientsService {
    public abstract void addUserIngredient(UserIngredientsDto request);
    public abstract void updateUserIngredient(UserIngredientsDto request);
    public abstract void removeUserIngredient(Long userId, Long ingredientId);
    public abstract void removeAllIngredientsForUser(Long userId);
}
