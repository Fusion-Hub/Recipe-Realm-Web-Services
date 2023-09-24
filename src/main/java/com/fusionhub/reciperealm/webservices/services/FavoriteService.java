package com.fusionhub.reciperealm.webservices.services;

import java.util.List;

import com.fusionhub.reciperealm.webservices.dto.FavoriteDto;

public interface FavoriteService {
    List<FavoriteDto> getFavoritesByUserId(Long userId);
    void addFavoriteForUser(Long userId, Long recipeId);
    void removeFavoriteForUser(Long userId, Long recipeId);
    boolean isRecipeFavoriteForUser(Long userId, Long recipeId);
}
