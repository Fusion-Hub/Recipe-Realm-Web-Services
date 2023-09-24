package com.fusionhub.reciperealm.webservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fusionhub.reciperealm.webservices.dto.FavoriteDto;
import com.fusionhub.reciperealm.webservices.services.FavoriteService;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController {
    
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteDto>> getFavoritesByUserId(@PathVariable Long userId) {
        List<FavoriteDto> favorites = favoriteService.getFavoritesByUserId(userId);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @PostMapping("/{userId}/{recipeId}")
    public ResponseEntity<Void> addFavoriteForUser(@PathVariable Long userId, @PathVariable Long recipeId) {
        favoriteService.addFavoriteForUser(userId, recipeId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/{recipeId}")
    public ResponseEntity<Void> removeFavoriteForUser(@PathVariable Long userId, @PathVariable Long recipeId) {
        favoriteService.removeFavoriteForUser(userId, recipeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userId}/{recipeId}/isFavorite")
    public ResponseEntity<Boolean> isRecipeFavoriteForUser(@PathVariable Long userId, @PathVariable Long recipeId) {
        boolean isFavorite = favoriteService.isRecipeFavoriteForUser(userId, recipeId);
        return new ResponseEntity<>(isFavorite, HttpStatus.OK);
    }
}
