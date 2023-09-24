package com.fusionhub.reciperealm.webservices.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.dto.FavoriteDto;
import com.fusionhub.reciperealm.webservices.mapping.FavoriteMapper;
import com.fusionhub.reciperealm.webservices.models.Favorite;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.repository.FavoriteRepository;
import com.fusionhub.reciperealm.webservices.repository.RecipeRepository;
import com.fusionhub.reciperealm.webservices.repository.UserRepository;
import com.fusionhub.reciperealm.webservices.services.FavoriteService;
import com.fusionhub.reciperealm.webservices.validation.FavoriteValidation;

@Service
public class FavoriteServiceImpl implements FavoriteService{

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private FavoriteValidation favoriteValidation;

    @Override
    public List<FavoriteDto> getFavoritesByUserId(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        return favorites.stream()
            .map(favoriteMapper::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public void addFavoriteForUser(Long userId, Long recipeId) {
  
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found!"));
        
        favoriteValidation.validateFavorite(userId, recipeId);
        
        Favorite newFavorite = new Favorite();
        newFavorite.setUser(user);
        newFavorite.setRecipe(recipe);
        favoriteRepository.save(newFavorite);
    }

    @Override
    public void removeFavoriteForUser(Long userId, Long recipeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found!"));
        Favorite favorite = favoriteRepository.findByUserAndRecipe(user, recipe)
            .orElseThrow(() -> new RuntimeException("Favorite not found!"));
        favoriteRepository.delete(favorite);
    }

    @Override
    public boolean isRecipeFavoriteForUser(Long userId, Long recipeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found!"));
        return favoriteRepository.findByUserAndRecipe(user, recipe).isPresent();
    }    

}